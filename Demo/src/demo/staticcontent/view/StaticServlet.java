/**
 * The sample code is intended for demo purposes only and no endorsement of Oracle implied and it is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.
 *
 * @author Juan Carlos Ruiz Rico <juan.carlos.r.ruiz.rico@oracle.com>
 * @date 12/22/2015
 * 
 */
package demo.staticcontent.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class StaticServlet extends HttpServlet {
    
    private String storageCloudURL;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputStream is = getServletContext().getResourceAsStream("/WEB-INF/object-storage.xml");
            Document doc = docBuilder.parse(is);
            doc.getDocumentElement().normalize();
            
            Element elm = (Element) doc.getElementsByTagName("provider-url").item(0);
            this.storageCloudURL = elm.getElementsByTagName("value").item(0).getTextContent();
            
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringTokenizer st = new StringTokenizer(request.getRequestURI(),"/");
        String uri = "";
 
        st.nextToken(); // Skip context-root from URI.
        while(st.hasMoreElements()){
            uri = uri.concat("/");
            uri = uri.concat(st.nextToken());
        }
        
        byte[] imageBytes = getImageAsBytes(uri);
        
        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }

    private byte[] getImageAsBytes(String uri) {
        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            url = new URL(storageCloudURL + uri);
            is = url.openStream();
            byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;
            
            while ((n = is.read(byteChunk)) > 0) {
                baos.write(byteChunk, 0, n);
            }
            
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
          if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }
}
