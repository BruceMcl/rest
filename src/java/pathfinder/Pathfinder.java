/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author BruLu
 */
@Path("pathfinder")
public class Pathfinder {

    @Context
    private UriInfo context;
    private ArrayList fileArray = new ArrayList();

    /**
     * Creates a new instance of Pathfinder
     */
    public Pathfinder() {
    }

    /**
     * Retrieves representation of an instance of pathfinder.Pathfinder
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        File currentDir = new File(".");
        listFilesAndFilesSubDirectories(currentDir);

        String result = String.join(" ", fileArray);
        return "<html><body><p>" + result + "</p></body></html>";
    }

    private void listFilesAndFilesSubDirectories(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    BasicFileAttributes basicAttr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    fileArray.add(("directory:" + file.getCanonicalPath()) + " Size: " + file.length() + " Created: "+basicAttr.creationTime()+ " Last Access: "+ basicAttr.lastAccessTime()+" Last Modified: "+basicAttr.lastModifiedTime());
                    listFilesAndFilesSubDirectories(file);
                } else {
                    BasicFileAttributes basicAttr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    fileArray.add(("directory:" + file.getCanonicalPath()) + " Size: " + file.length() + " Created: "+basicAttr.creationTime()+ " Last Access: "+ basicAttr.lastAccessTime()+" Last Modified: "+basicAttr.lastModifiedTime());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * PUT method for updating or creating an instance of Pathfinder
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

}
