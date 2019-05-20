package pngstore.command;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class RestoreFromPng implements Command{

	@Override
	public void execute(Path source, Path destination) {
		try {
			unpackImageToFile(source, destination);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void unpackImageToFile(Path sourceImage, Path destinationFile) throws IOException {
		if (!sourceImage.toFile().exists())
			throw new FileNotFoundException("File " + sourceImage.toString() + " does not exist.");
		BufferedImage image = ImageIO.read(sourceImage.toFile());
		byte[] imageArray = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		int fileLength = (((int)imageArray[0]) & 0xFF);
		fileLength    += (((int)imageArray[1]) & 0xFF) << 8;
		fileLength    += (((int)imageArray[2]) & 0xFF) << 16;
		fileLength    += (((int)imageArray[3]) & 0xFF) << 24;
		try (OutputStream outputStream = Files.newOutputStream(destinationFile)) {
			outputStream.write(imageArray, 4, fileLength);
		}
	}

}