package action;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TextureRemap {

	public static void main(String[] args) throws Exception {
		File folder = new File("./zin");
		for (var file : folder.listFiles()) {
			var img = ImageIO.read(file);
			var out = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			for (int x = 0; x < img.getWidth(); x++) {
				for (int z = 0; z < img.getHeight(); z++) {
					out.setRGB(x + 3, z + 1, img.getRGB(x, z));
				}
			}
			var dst = new File("./zout/" + file.getName());
			if (!dst.exists()) dst.createNewFile();
			ImageIO.write(out, "PNG", new File("./zout/" + file.getName()));
		}
	}

}
