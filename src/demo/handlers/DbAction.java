package demo.handlers;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentTcFile;
import com.teamcenter.rac.kernel.TCException;

import demo.util.ORProgressBarThread;




public class DbAction  {
	
	private static String selecteddir = "C:"+"\\"+"Users"+"\\"+"Administrator"+"\\"+"Downloads";
	private ORProgressBarThread	progressbarthread;
	public  void DO( final TCComponentDataset targetComponents) {
		System.out.println("进入do方法");
		
		Thread thread=null;
		thread = new Thread(){
			public void run() {
		
		String defaultpath = selecteddir+"\\"+getDate_s();
		System.out.println("defaultpath:"+defaultpath);
		File txtfile =new File(defaultpath);    
	     if  (!txtfile .exists()  && !txtfile .isDirectory())      
	     {     
	     	txtfile .mkdirs();   
	     }
	     System.out.println(targetComponents+":targetComponents");
			if (targetComponents!=null) {
				String watermarkname =  " 江苏太平洋精锻科技股份有限公司 " + getDate();
				progressbarthread=new ORProgressBarThread("进度", "正在下载文件......");
				progressbarthread.start();
				
                	
						String datasettype = null;
						try {
							datasettype = targetComponents.getProperty("object_type");
						} catch (TCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String datasetname = null;
						try {
							datasetname = targetComponents.getProperty("object_name");
						} catch (TCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String WORDEXCELPATH = defaultpath+"\\更改文件\\";
						System.out.println(datasettype+":datasettype");
						if (datasettype.contains("MS ExcelX")) {
							System.out.println("-----------------");
							File file = DownLoadFileToDir(targetComponents, datasetname, WORDEXCELPATH);
							
						}
					
                	//isSelectionTempFile = false;
				
			}
			progressbarthread.setBool(true);
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		};thread.start();
		
		System.out.println("下载完成");
		
	}
	
	public static File DownLoadFileToDir(TCComponentDataset dataSet, String filename, String dir) {
		File downFile = null;
		try {
			File tFile = new File(dir, filename);
			if (tFile.exists())
				tFile.delete();

			// 创建临时文件夹
			File tempDic = new File(dir);
			if (!tempDic.exists()) {
				tempDic.mkdirs();
			}

			System.out.println("准备下载数据集");
			String type = dataSet.getProperty("object_type").toString();
			TCComponentTcFile[] tcTiles = dataSet.getTcFiles();
			
			if (tcTiles == null)
				return null;

			System.out.println("数据集tcTiles文件个数 " + tcTiles.length);
			
			for (int iTCFile = 0; iTCFile < tcTiles.length; iTCFile++) {
				if (type.equalsIgnoreCase("MS Excel")){
					TCComponentTcFile tcFile = tcTiles[iTCFile];
					if (tcFile == null) {
						continue;
					}
					filename += ".xls";
					downFile = tcFile.getFile(dir, filename);
					break;
				}else if (type.equalsIgnoreCase("MS ExcelX")){
					TCComponentTcFile tcFile = tcTiles[iTCFile];
					if (tcFile == null) {
						continue;
					}
					filename += ".xlsx";
					downFile = tcFile.getFile(dir, filename);
					break;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return downFile;
	}
	
	public static String getDate(){
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = df.format(date);
		return str;
	}
	public static String getDate_s(){
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = df.format(date);
	    
		return str;
	}
}
