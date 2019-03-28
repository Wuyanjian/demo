package demo.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.util.MessageBox;



public class DbaHandler extends AbstractHandler implements IHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("已触发方法");
		TCComponentFolder chooseFolder = null;
		TCComponentDataset templateDataset = null;
		File targetFile = null;
		String property =null;
		String subType=null;
		InterfaceAIFComponent[] targets = AIFDesktop.getActiveDesktop().getCurrentApplication().getTargetComponents();
		System.out.println("targets"+targets);
		if (targets == null || targets.length == 0) {
			MessageBox.post("对不起，您没有选择任何对象，请您先选择对象再进行操作!","错误",MessageBox.ERROR);
			return null;
		}
		if (targets.length > 1) {
			MessageBox.post("对不起,请选择单个对象进行操作!","错误",MessageBox.ERROR);
			return null;
		}
		try {
			 property = ((TCComponent)targets[0]).getProperty("object_name");
		} catch (TCException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 subType = ((TCComponent)targets[0]).getSubType();
			 if (!(subType.equals("MSExcelX"))) {
				 MessageBox.post("对不起,请选择类型为MSExcelX文件!","错误",MessageBox.ERROR);
					return null;
			}
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		templateDataset = (TCComponentDataset) targets[0];
		System.out.println(property+"property");
		
		
		new DbAction().DO(templateDataset);
		return null;
	}

}
