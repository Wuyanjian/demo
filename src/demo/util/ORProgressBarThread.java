package demo.util;



public class ORProgressBarThread extends Thread{
	private ORProgressBar bar;

	private String title;

	public ORProgressBarThread(String title,String showLable) {
		this.title = title;
		bar = new ORProgressBar(showLable);
	}

	public void run() {
		bar.setTitle(title);
		bar.initUI();
	}

	public void setBool(boolean bool) {
		bar.setBool(true);
	}
}
