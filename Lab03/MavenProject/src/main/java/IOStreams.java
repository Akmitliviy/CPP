import java.io.*;
import java.util.List;

public class IOStreams {
    public static void writeMessagesToFile(String filename, List<Message> messages) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
            oos.writeObject(messages);
        }finally {
            if(fos != null){
                fos.close();
            }
            if(oos != null){
                oos.close();
            }
        }
    }

    public static List<Message> readMessagesFromFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try{
            return (List<Message>) ois.readObject();
        }finally {
            if(fis != null){
                fis.close();
            }
            if(ois != null){
                ois.close();
            }
        }
    }

    public static void writeUserProfileToFile(String filename, UserProfile userProfile) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename));
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        try {
            oos.writeObject(userProfile);
        }finally {
            if(bos != null){
                bos.close();
            }
            if(oos != null){
                oos.close();
            }
        }
    }

    public static UserProfile readUserProfileFromFile(String filename) throws IOException, ClassNotFoundException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
        ObjectInputStream ois = new ObjectInputStream(bis);
        try {
            return (UserProfile) ois.readObject();
        }finally {
            if(bis != null){
                bis.close();
            }
            if(ois != null){
                ois.close();
            }
        }
    }
}
