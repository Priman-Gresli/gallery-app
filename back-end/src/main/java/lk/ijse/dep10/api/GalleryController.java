package lk.ijse.dep10.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/images")
public class GalleryController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public ArrayList<String> getImage(UriComponentsBuilder uriBuilder) {
        ArrayList<String> imageList = new ArrayList<>();
        String urlName = servletContext.getRealPath("/uploads");
        File imageFile = new File(urlName);
        String[] list = imageFile.list();
        for (String name : list) {
            UriComponentsBuilder uriComponentsBuilder = uriBuilder.cloneBuilder();
            String images = uriComponentsBuilder.pathSegment("uploads", name).toUriString();
            imageList.add(images);
        }
        System.out.println(imageFile);
        return imageList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<String> saveImage(@RequestPart("images") List<Part> imageFile, UriComponentsBuilder uriComponentsBuilder){
        ArrayList<String> imageList = new ArrayList<>();
        if (imageFile != null) {
            String urlName = servletContext.getRealPath("/uploads");
            for (Part image : imageFile) {
                String absolutePath = new File(urlName, image.getSubmittedFileName()).getAbsolutePath();
                try {
                    image.write(absolutePath);
                    UriComponentsBuilder uriComponentsBuilder1 = uriComponentsBuilder.cloneBuilder();
                    String url = uriComponentsBuilder1.pathSegment("uploads", image.getSubmittedFileName()).toUriString();
                    imageList.add(url);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageList;
    }


}
