package pngstore;

import pngstore.exceptions.InvalidPNGSourceFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

public class PngStoreManager {
	private static final String SIGNATURE = "PNGARCH";
	private Path sourceFile;
	private Path destinationFile;

	public PngStoreManager(Path sourceFile, Path destinationFile) {
		this.sourceFile = sourceFile;
		this.destinationFile = destinationFile;
	}

	private BufferedImage createImage(Path sourceFile) {
		int imageDimension = 1 + (int)Math.sqrt(5.0 + sourceFile.getFileName().toString().length() + sourceFile.toFile().length());
		return new BufferedImage(imageDimension, imageDimension, BufferedImage.TYPE_BYTE_GRAY);
	}

	private ByteBuffer getImageBuffer(BufferedImage image) {
		byte[] imageArray = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		return ByteBuffer.wrap(imageArray);
	}

	private void writeImageHeader(ByteBuffer buffer, Path srcFile) {
		for (char character : SIGNATURE.toCharArray()) {
			buffer.putChar(character);
		}
		buffer.putChar((char)0);
		buffer.putInt((int)srcFile.toFile().length());
		for (char character : srcFile.getFileName().toString().toCharArray()) {
			buffer.putChar(character);
		}
		buffer.putChar((char)0);
	}

	public void packFileToPng() throws IOException {
		BufferedImage image = createImage(sourceFile);
		ByteBuffer buffer = getImageBuffer(image);
		writeImageHeader(buffer, sourceFile);
		loadFileInImageBuffer(buffer, sourceFile);
		saveImageBufferToPng(image, destinationFile);
	}

	private void loadFileInImageBuffer(ByteBuffer buffer, Path sourceFile) throws IOException {
		try (InputStream inputStream = Files.newInputStream(sourceFile)) {
			inputStream.read(buffer.array(), buffer.position(), (int)sourceFile.toFile().length());
		}
	}

	private void saveImageBufferToPng(BufferedImage image, Path destinationFile) throws IOException {
		ImageIO.write(image, "png", destinationFile.toFile());
	}

	private void saveImageBufferToFile(ByteBuffer buffer, Path destinationFile, int fileLength) throws IOException{
		try (OutputStream outputStream = Files.newOutputStream(destinationFile)) {
			outputStream.write(buffer.array(), buffer.position(), fileLength);
		}
	}

	public void unpackPngToFile() throws IOException {
		BufferedImage image = loadPngFileInImageBuffer(sourceFile);
		ByteBuffer byteBuffer = getImageBuffer(image);
		checkPngSignature(byteBuffer);
		int fileSize = byteBuffer.getInt();
		readStringFromBuffer(byteBuffer);
		saveImageBufferToFile(byteBuffer, destinationFile, fileSize);
	}

	private BufferedImage loadPngFileInImageBuffer(Path sourceFile) throws IOException {
		return ImageIO.read(sourceFile.toFile());
	}

	private void checkPngSignature(ByteBuffer byteBuffer) {
		if (!readStringFromBuffer(byteBuffer).equals(SIGNATURE))
			throw new InvalidPNGSourceFile("No payload in " + sourceFile.toString());
	}

	private String readStringFromBuffer(ByteBuffer byteBuffer) {
		StringBuilder string = new StringBuilder();
		char character;
		while ((character = byteBuffer.getChar()) != 0) {
			string.append(character);
		}
		return string.toString();
	}
}
