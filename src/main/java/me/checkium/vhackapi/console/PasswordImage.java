package me.checkium.vhackapi.console;

import java.awt.image.BufferedImage;
import java.io.IOException;

//TODO: cleanup code
public class PasswordImage extends Image {

    private String text = "";

    public PasswordImage(String base64String) throws IOException {
        super(base64String);

        readInPassword();
    }


    public PasswordImage(BufferedImage image)
    {
        super(image);

        readInPassword();
    }

    private void readInPassword() {
        Letters letters = Letters.getInstance();

        for(int i=1; i<11; i++)
        {
            BufferedImage subImage = image.getSubimage(9 * i, 15, 8, 12);

            if(letters.getCharFor(generateHashFor(subImage)) == ' ')
            {
                throw new IllegalArgumentException("One of the characters is unkown at the moment.");
            }
            else
            {
                text += letters.getCharFor(generateHashFor(subImage));
            }
        }
    }

    public String getOCRString()
    {
        return text;
    }
}