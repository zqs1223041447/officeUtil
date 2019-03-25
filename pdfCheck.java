import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;

//检查pdf文件是否损坏
public static boolean check(String file) {  
        boolean flag1 = false;  
        int n = 0;  
        try {  
            Document document = new Document(new PdfReader(file).getPageSize(1));  
            document.open();  
            PdfReader reader = new PdfReader(file);  
            n = reader.getNumberOfPages();  
            if (n != 0)  
                flag1 = true;              
        } catch (IOException e) {  
            e.printStackTrace();  
           
        }finally{
			document.close();
		}
        return flag1;  
    }  
	
//检查PDF文件是否有文字内容并输出。如果只含有图片则返回空
public static String hasText(String filePath) throws Exception {
		String ts = "";
		File f = new File(filePath);
		if (!f.exists()) {
			return ts;
		}
		try {
			PDDocument pdfdocument = PDDocument.load(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out);
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.writeText(pdfdocument, writer);
			byte[] contents = out.toByteArray();
			ts = new String(contents);
			// System.out.println(f.getName() + "length is:" + contents.length
			// + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			if (pdfdocument!=null) {
				pdfdocument.close();
				out.close();
				writer.close();
			}
			return ts;
		}
	}