package testXdocreport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

public class Application {
	public static void main(String[] args) {
	    try {
	      // 1) Load file by filling Velocity template engine and cache it to the registry
	      InputStream in = Application.class.getResourceAsStream("DocxProjectWithVelocity.docx");
	    	//InputStream in = new FileInputStream(new File("C:/DocxProjectWithVelocity.docx"));
	      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
	      

	      // 2) Create context Java model
	      IContext context = report.createContext();
	      Test project = new Test("TESTING XDOCREPORT");
	      context.put("project", project);

	      // 3) Generate report
	      OutputStream out = new FileOutputStream(new File("DocxProjectWithVelocity_out.docx"));
	      report.process(context, out);
	      out.close();

	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (XDocReportException e) {
	      e.printStackTrace();
	    }
	  }
	public static class Test{
		private String name;
		
		public Test(String name){
			this.name=name;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String name){
			this.name=name;
		}
	}
}
