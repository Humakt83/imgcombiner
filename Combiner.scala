import java.io.File
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

object Combiner {

    private val combinedImageName = "combined.png"
    
    def main(args: Array[String]) {
        if (args.length < 1) {
            println("You need to provide a path to folder where images to combine are as an argument")
            return
        }
        val folder = args.apply(0)
        if (!new File(folder).isDirectory()) {
            println(folder + " is not a directory")
            return
        }
        val files = new File(folder + "/").listFiles()
        if (files.length < 0) {
            println("Folder contains no files to combine")
            return
        }
        println("Combining " + files.length + " images")
        val images = files.sorted.map(ImageIO.read(_))
        val width = images.apply(0).getWidth()
        val totalWidth = width * images.length
        val height = images.apply(0).getHeight()
        val combined = new BufferedImage(totalWidth, height, BufferedImage.TYPE_INT_ARGB)
        val g = combined.createGraphics()
        var x = 0
        images.foreach { image => { 
            g.drawImage(image, x, 0, null)
            x += width
        }}
        ImageIO.write(combined, "png", new File(folder + "/" + combinedImageName))
    }
}
