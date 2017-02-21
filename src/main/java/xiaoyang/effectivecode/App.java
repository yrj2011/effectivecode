package xiaoyang.effectivecode;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
@Deprecated
public class App {
	public static void main(String[] args) {
		String path = "E:/workspace-java8/effectivecode/src/main/java/xiaoyang";
		File sources = new File(path);
		if (sources.isDirectory()) {
			depDrec("", sources);
		}
	}

	public static void depDrec(String name, File sources) {
		if (name != null && name.length() > 0) {
			name += ".";
		}
		name += sources.getName();
		File[] files = sources.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					depDrec(name, file);
				} else {
					String pkg ="package " +  name+";";
					System.out.println(pkg);
					appendA(file , pkg);
				}
			}
		}
	}

	public static void writhPakg() {

	}

	/**
	 * A方法追加文件：使用RandomAccessFile
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            追加的内容
	 */
	public static void appendA(File file, String content) {
		try {
			File file3 = new File(file.getCanonicalPath());
			String path2 =file.getCanonicalPath().replaceAll("xiaoyang", "xiaoyanb_b");
			String path3 = path2.substring(0,path2.lastIndexOf("\\"));
			File file4 = new File(path3);
			if(!file4.exists()){
				file4.mkdirs();
			}
			File file2 = new File(path2);
			if(file2.exists()){
				file2.delete();
			}
			file2.createNewFile();
			FileWriter fw = new FileWriter(file2);
			fw.write(content+"\n");
			FileReader fr = new FileReader(file);
			byte[] buffer = new byte[1024];
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String data = null;
			while((data = br.readLine())!=null)
			{
				fw.write(data+"\n");
			}
			boolean delete = file.delete();
			System.out.println(delete);
			fw.flush();
			
			fw.close();
			br.close();
			
			boolean rs = file2.renameTo(file3);
			System.out.println(rs);
			/*
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(file, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			//randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
