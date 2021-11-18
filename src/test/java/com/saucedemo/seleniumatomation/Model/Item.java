package com.saucedemo.seleniumatomation.Model;

import lombok.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private String name;
    private byte[] image;
    private String description;
    private Double price;



    public byte[] readImageFromUrl(String urlText) throws Exception {
        System.out.println("myurl ="+urlText);

        URL url = new URL(urlText);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }

        return output.toByteArray();
    }
}
