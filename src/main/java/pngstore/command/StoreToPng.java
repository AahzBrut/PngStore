package pngstore.command;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StoreToPng implements Command {

	@Override
	public void execute(Path source, Path destination) {
		try {
			packFileToImage(source, destination);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private static void packFileToImage(Path sourceFile, Path destinationImage) throws IOException {
		if (!sourceFile.toFile().exists())
			throw new FileNotFoundException("File " + sourceFile.toString() + " does not exist.");
		if (sourceFile.toFile().length() > Integer.MAX_VALUE)
			throw new RuntimeException("File too big (2 Gb max)");
		int fileLength = (int)sourceFile.toFile().length();
		int dimension = ((int)Math.sqrt(fileLength + 4.0)) + 1;
		BufferedImage image = new BufferedImage(dimension, dimension, BufferedImage.TYPE_BYTE_GRAY);
		byte[] imageArray = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		imageArray[0] = (byte) (fileLength & 0xFF);
		imageArray[1] = (byte) (fileLength >>> 8 & 0xFF);
		imageArray[2] = (byte) (fileLength >>> 16 & 0xFF);
		imageArray[3] = (byte) (fileLength >>> 24 & 0xFF);
		System.arraycopy(readSourceFile(sourceFile), 0, imageArray, 4, fileLength);
		ImageIO.write(image, "png", destinationImage.toFile());
	}

	private static byte[] readSourceFile(Path sourceFile) throws IOException {
		byte[] fileBuffer;
		try (InputStream inputStream = Files.newInputStream(sourceFile)) {
			fileBuffer = new byte[inputStream.available()];
			inputStream.read(fileBuffer);
		}
		return fileBuffer;
	}


}