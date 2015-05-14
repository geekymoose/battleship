/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.asset;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import java.awt.Dimension;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;



/**
 * <h1>DOMParser</h1>
 * <p>public class DOMParser</p>
 * <p>
 * DOMParser read xml file and return matched data.
 * </p>
 * 
 * @since   Mar 28, 2015
 * @author  Constantin MASSON
 * 
 * @see Config
 */
public class DOMParser {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private static DOMParser singleton = null;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /*
     * Private constructor
     * Patter singleton
     */
    private DOMParser(){
    }
    
    /**
     * Create and initialize the DOM Parser. 
     * It will create the DOMParser singleton. This function must be called before 
     * using any of DOMParser function!!
     */
    public static void createDOMParser(){
        DOMParser.singleton = new DOMParser();
    }
    
    
    
    
    
    //**************************************************************************
    // Inner usefull functions
    //**************************************************************************
    /*
     * Return a DOM Document
     * @param pFileName DOM document path
     * @return  DOM document created
     * @throws ExecError 
     */
    private static Document getDoc(String pFileName) throws ExecError{
        Document                document;
        DocumentBuilder         builder;
        DocumentBuilderFactory  builderFactory ;
        
        try {
            builderFactory  = DocumentBuilderFactory.newInstance();
            builder         = builderFactory.newDocumentBuilder();
            document        = builder.parse(new FileInputStream(pFileName));
            
            document.getDocumentElement().normalize();
            return document; 
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new ExecError(); //Att error message!!
        }
    }
    
    
    /*
     * Look for all element owned by root (Direct or far child of root) and 
     * with a specific name
     * @param root      root where to look for elements
     * @param sought    element's name to find
     * @return ArrayList of found element (Empty if no element found)
     */
    private static ArrayList<Element> getAllRecursiveChildElement(Element root, 
                                                                  String sought,
                                                                  String... filter){
        ArrayList<Element> list = new ArrayList();
        if(root == null){
            return list;
        }
        else if(root.getNodeName().equals(sought)){
            if(filter != null && filter.length == 2){
                if((root.getAttribute(filter[0])).equals(filter[1])){
                    list.add(root);
                }
            }else{
                list.add(root);
            }
            return list;
        }
        else{
            NodeList child = root.getChildNodes();
            for(int k=0; k<child.getLength(); k++){
                Node nNode      = child.item(k);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement    = (Element) nNode;
                    list.addAll(getAllRecursiveChildElement(eElement, sought, filter));
                }
            }
        }
        return list;
    }
    
    
    /*
     * @deprecated ATTENTION : Not yet tested (Should work be on never knows)
     * Look for the first xml Element owned by root. It search recursively in 
     * all root node and so on.
     * @param root      root where to look for element
     * @param sought    element's name to find
     * @return element if found, otherwise, return null
     */
    private static Element getFirstRecursiveChildElement(Element root, String sought){
        if(root == null || root.getNodeName().equals(sought)){
            return root;
        }
        else{
            NodeList child = root.getChildNodes();
            for(int k=0; k<child.getLength(); k++){
                Node nNode      = child.item(k);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement    = (Element) nNode;
                    Element find        = getFirstRecursiveChildElement(eElement, sought);
                    if(find!= null){
                        return find;
                    }
                }
            }
        }
        return null;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return all constants in the path given. Recover only string constants 
     * (Means the constants in string attribute from constants xml file)
     * @param pPath file where to find constants
     * @return HashMap with constants (key and value are String format)
     * @throws ExecError throws if unable to load constants
     */
    public static HashMap<String, String> getStringConstants(String pPath) 
    throws ExecError{
        HashMap<String, String> list    = new HashMap();
        
        Document            doc         = DOMParser.getDoc(pPath);
        Element             constants   = (Element)doc.getElementsByTagName("constants").item(0);
        Element             string      = (Element)constants.getElementsByTagName("string").item(0);
        ArrayList<Element>  listValue   = DOMParser.getAllRecursiveChildElement(string, "val");
        
        for(Element e : listValue){
            String key      = e.getAttribute("id");
            String value    = e.getTextContent();
            DebugTrack.showInitConstant(key, value);
            list.put(key, value);
        }
        return list;
    }
    
    
    /**
     * Return all constants in the path given. Recover only Integer constants 
     * (Means the constants in integer attribute from constants xml file)
     * @param pPath file where to find constants
     * @return HashMap with constants (key is String, value is Integer format)
     * @throws ExecError throws if unable to load constants
     */
    public static HashMap<String, Integer> getIntegerConstants(String pPath) 
    throws ExecError{
        HashMap<String, Integer> list    = new HashMap();
        
        Document            doc         = DOMParser.getDoc(pPath);
        Element             constants   = (Element)doc.getElementsByTagName("constants").item(0);
        Element             integer     = (Element)constants.getElementsByTagName("integer").item(0);
        ArrayList<Element>  listValue   = DOMParser.getAllRecursiveChildElement(integer, "val");
        
        for(Element e : listValue){
            String  key     = e.getAttribute("id");
            int     value   = Integer.valueOf(e.getTextContent());
            DebugTrack.showInitConstant(key, value);
            list.put(key, value);
        }
        return list;
    }
    
    
    /**
     * Return all constants in the path given. Recover only Integer constants 
     * (Means the constants in integer attribute from constants xml file)
     * @param pPath file where to find constants
     * @return HashMap with constants (key is String, value is Integer format)
     * @throws ExecError throws if unable to load constants
     */
    public static HashMap<String, Double> getDoubleConstants(String pPath) 
    throws ExecError{
        HashMap<String, Double> list    = new HashMap();
        
        Document            doc         = DOMParser.getDoc(pPath);
        Element             constants   = (Element)doc.getElementsByTagName("constants").item(0);
        Element             integer     = (Element)constants.getElementsByTagName("double").item(0);
        ArrayList<Element>  listValue   = DOMParser.getAllRecursiveChildElement(integer, "val");
        
        for(Element e : listValue){
            String  key     = e.getAttribute("id");
            double  value   = Double.valueOf(e.getTextContent());
            DebugTrack.showInitConstant(key, value);
            list.put(key, value);
        }
        return list;
    }
    
    
    /**
     * Return all constants in the path given. Recover only Integer constants 
     * (Means the constants in integer attribute from constants xml file)
     * @param pPath file where to find constants
     * @return HashMap with constants (key is String, value is Integer format)
     * @throws ExecError throws if unable to load constants
     */
    public static HashMap<String, Dimension> getDimensionConstants(String pPath) 
    throws ExecError{
        HashMap<String, Dimension> list    = new HashMap();
        
        Document            doc         = DOMParser.getDoc(pPath);
        Element             constants   = (Element)doc.getElementsByTagName("constants").item(0);
        Element             dim         = (Element)constants.getElementsByTagName("dimension").item(0);
        ArrayList<Element>  listValue   = DOMParser.getAllRecursiveChildElement(dim, "val");
        
        for(Element e : listValue){
            int         width   = Integer.valueOf(e.getAttribute("width"));
            int         height  = Integer.valueOf(e.getAttribute("height"));
            String      key     =  e.getAttribute("id");
            Dimension value     = new Dimension(width, height);
            DebugTrack.showInitConstant(key, value);
            list.put(key, new Dimension(width, height));
        }
        return list;
    }
    
    
    /**
     * Create and return an ArrayList with all theme image names. Image have 
     * different type as static and dynamic. Return all image from type given, 
     * for example, if type is "static", every image with type="static" will 
     * be added in ArrayList (And only these one)
     * HashMap format : key = name / value = ext
     * @param pPath     file where to find image
     * @param pType     type of image to get (static / dynamic)
     * @return ArrayList with image names
     * @throws ExecError throws if unable to load constants
     */
    public static HashMap<String, String> getThemeImgName(String pPath, String pType)
    throws ExecError{
        HashMap<String, String> list    = new HashMap();
        Document            doc         = DOMParser.getDoc(pPath);
        Element             img         = (Element)doc.getElementsByTagName("image").item(0);
        ArrayList<Element>  listValue   = DOMParser.getAllRecursiveChildElement(img, 
                                                                                "img", 
                                                                                "type", 
                                                                                pType);
        
        for(Element e : listValue){
            String  name    = e.getTextContent();
            String  ext     = e.getAttribute("ext");
            DebugTrack.showInitData(pType+" image", name+"."+ext);
            list.put(name, ext);
        }
        return list;
    }
}

