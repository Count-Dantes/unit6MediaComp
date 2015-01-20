import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  
  /** Method to set the Red to 0 */
  public void zeroRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to set the Red to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set the Red to 0 */
  public void zeroGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
      }
    }
  }
  
  
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(255-pixelObj.getBlue());
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
      }
    }
  }
  
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    int averageValue = 0;
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        averageValue = pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen();
        averageValue = averageValue / 3;
        pixelObj.setBlue(averageValue);
        pixelObj.setRed(averageValue);
        pixelObj.setGreen(averageValue);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  //mirror horizontal
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottemPixel = null;
    int length = pixels.length;
    int width = pixels[0].length;
    for (int row = 0; row < length/2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottemPixel = pixels[length - 1 - row][col];
        bottemPixel.setColor(topPixel.getColor());
      }
    } 
  }
  //mirror horizontal
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottemPixel = null;
    int length = pixels.length;
    int width = pixels[0].length;
    for (int row = 0; row < length/2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottemPixel = pixels[length - 1 - row][col];
        topPixel.setColor(bottemPixel.getColor());
      }
    } 
  }
  //mirror diagonal
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int whitespace = 0;
    int length = pixels.length;
    int width = pixels[0].length;
    for(int row = 0; row < length; row ++)
    {
        for(int col = whitespace; col < length; col ++)
        {
            topPixel = pixels[row][col];
            botPixel = pixels[col][row];
            Color botColor = botPixel.getColor();
            botPixel.setColor(botPixel.getColor());
            topPixel.setColor(botColor);
        }
        whitespace +=1;
    }
}

public void mirrorDiagonalOffset()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int whitespace = 0;
    int length = pixels.length;
    int width = pixels[0].length;
    int difference = width-length;
    for(int row = difference; row <  difference + length; row ++)
    {
        for(int col = whitespace; col < length; col ++)
        {
            topPixel = pixels[row][col];
            botPixel = pixels[col][row];
            Color botColor = botPixel.getColor();
            botPixel.setColor(botPixel.getColor());
            topPixel.setColor(botColor);
        }
        whitespace +=1;
    }
}

public void posterize()
{
    Pixel[][] pixels = this.getPixels2D();
    int length = pixels.length;
    int width = pixels[0].length;
    int redValue = 0;
    int blueValue = 0;
    int greenValue = 0;
    int tempColor = 0;
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        greenValue = pixelObj.getGreen();
        redValue = pixelObj.getRed();
        blueValue = pixelObj.getBlue();
        
        if (greenValue < 60)
        {
            pixelObj.setGreen(30);
        }
        else if(greenValue < 120)
        {
            pixelObj.setGreen(90);
        }
        else if(greenValue < 180)
        {
            pixelObj.setGreen(150);
        }
        else if(greenValue<255)
        {
            pixelObj.setGreen(210);
        }
        
        if (blueValue < 60)
        {
            pixelObj.setBlue(30);
        }
        else if(blueValue < 120)
        {
            pixelObj.setBlue(90);
        }
        else if(blueValue < 180)
        {
            pixelObj.setBlue(150);
        }
        else if(blueValue<255)
        {
            pixelObj.setBlue(210);
        }
        
        if (redValue < 60)
        {
            pixelObj.setRed(30);
        }
        else if(redValue < 120)
        {
            pixelObj.setRed(90);
        }
        else if(redValue < 180)
        {
            pixelObj.setRed(150);
        }
        else if(redValue<255)
        {
            pixelObj.setRed(210);
        }
     
    }
}
}
/*
public void randomize(int offset)
{
    Pixel[][] pixels = this.getPixels2D();
    int length = pixels.length;
    int width = pixels[0].length;
    int redValue = 0;
    int blueValue = 0;
    int greenValue = 0;
    int tempColor = 0;
    for (Pixel[] rowArray : pixels)
    {
        for (Pixel pixelObj : rowArray)
        {
            greenValue = pixelObj.getGreen();
            redValue = pixelObj.getRed();
            blueValue = pixelObj.getBlue();
            if (100*Math.random() < 10)
            {
                if ( ( (int) Math.random()*1) == 0)
                {
                    greenValue = greenValue + ( (int) Math.random()* offset);
                    if (greenValue > 255)
                    {
                        greenValue = 25;
                    }
                }
                if ( ( (int) Math.random()*1) == 1)
                {
                    greenValue = greenValue - ( (int) Math.random()* offset);
                    if (greenValue < 0)
                    {
                        greenValue = 0;
                    }
                }     
        }
    }
}
}
*/  

public void mirrorDiagonalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int whitespace = 0;
    int length = pixels.length;
    int width = pixels[0].length;
    for(int row = length; row >0; row --)
    {
        for(int col = length; col > whitespace; col --)
        {
            topPixel = pixels[row][col];
            botPixel = pixels[col][row];
            Color botColor = botPixel.getColor();
            botPixel.setColor(botPixel.getColor());
            topPixel.setColor(botColor);
        }
        whitespace +=1;
    }
}
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  void cropAndCopy( Picture sourcePicture,
                    int startSourceRow,
                    int endSourceRow,
                    int startSourceCol,
                    int endSourceCol,
                    int startDestRow,
                    int startDestCol )
  {
      Pixel[][] destPixels = this.getPixels2D();
      Pixel[][] sourcePixels = sourcePicture.getPixels2D();
      int row = startSourceRow;
      int col = startSourceCol;
      int destRow = startDestRow;
      int destCol = startDestCol;
      for(int i = startSourceRow; i < endSourceRow; i++)
      {
          for(int j = startSourceCol; j < startSourceCol; j++)
          {
              destPixels[destRow][destCol] = sourcePixels[row][col];
              destRow ++;
              destCol ++;
              row ++;
              col ++;
              
          }         
      }
  }
                   
  
  public void scalePic(Picture fromPic, double scaleX, double scaleY)
  {
      fromPic.scale(scaleX, scaleY);      
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture arpan = new Picture("arpan.jpg");
    Picture snowman = new Picture("snowman.jpg");
    //arpan.scalePic(arpan,.01,.01);
    arpan = arpan.scale(.25,.25);
    arpan.explore();
    snowman.negate();
    snowman.explore();
    arpan.cropAndCopy(snowman, 50,1000,1,1000,20,34);
    //arpan.randomize(50);
    //arpan.explore();


    //beach.zeroBlue();
    //arpan.posterize();
    arpan.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
