package br.com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public String getPathADB() {
		String sysvar = System.getenv("ANDROID_HOME");
		sysvar = sysvar.concat("\\platform-tools\\");
		return sysvar;
	}

	public String getDesktopUsuarioLogado() {
		String pathUsuarioLogado = "C:\\Users\\"
				+ System.getProperty("user.name") + "\\Desktop\\";
		return pathUsuarioLogado;
	}

	public String geraCaminhoParaCriarArquivosLog() {
		String caminhoLog = getDesktopUsuarioLogado();
		caminhoLog = caminhoLog.concat("Logs\\");
		return caminhoLog;
	}
	
	public String geraCaminhoScripts(){
		String caminhoScripts = getDesktopUsuarioLogado();
		caminhoScripts = caminhoScripts.concat("Monkey_Exerciser\\");
		return caminhoScripts;
	}

	public List<String> getDevice() {
		try {
			ProcessBuilder pb = new ProcessBuilder("adb", "devices");
			pb.redirectErrorStream(true);
			Process p = pb.start();
			p.waitFor();
			BufferedReader out = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			List<String> listadevices = new ArrayList<String>();
			out.readLine();

			while (out.ready()) {
				String devices = (out.readLine()).replace("device", "");
				if (!devices.equals("")) {
					listadevices.add(devices);
				}
			}
			out.close(); // Close output stream (good practice)
			return listadevices;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void criaArquivoBatParaExecucaoMonkey() {
		try {
			criaDiretorioScripts();
			File file = new File(geraCaminhoScripts()+"MonkeyScript.bat");
			if (file.exists()) {
				System.out.println("Arquivo não criado com sucesso.");
			} else {
				file.createNewFile();
				System.out.println("Arquivo criado com sucesso.");
			}
		} catch (IOException e) {
		}
	}
	
	public void criaArquivoVBSGetData() {
		try {
			criaDiretorioScripts();
			File file = new File(geraCaminhoScripts()+"getdate.vbs");
			if (file.exists()) {
				System.out.println("Arquivo não criado com sucesso.");
			} else {
				file.createNewFile();
				System.out.println("Arquivo criado com sucesso.");
			}
		} catch (IOException e) {
		}
	}
	
	public void escreverNoArquivoVBS() {
		try {
			criaArquivoVBSGetData();
			BufferedWriter out = new BufferedWriter(new FileWriter(getNomeBatVBSGetData()));
			out.write("strHour = Hour(Now())");
			out.write("\nif len(strHour) = 1 Then strHour = \"0\" & strHour End If ");
			out.write("\nstrMinute = Minute(Now())");
			out.write("\nIf len(strMinute) = 1 Then strMinute = \"0\" & strMinute End If");
			out.write("\nstrSecond = Second(Now())");
			out.write("\nIf len(strSecond) = 1 Then strSecond = \"0\" & strSecond End If");
			out.write("\nWScript.Echo Date & \"_\" & strHour & \"-\" & strMinute & \"-\" & strSecond");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getNomeBatExecucaoMonkey(){
		String nomeArquivo = geraCaminhoScripts()+"MonkeyScript.bat";
		return nomeArquivo;
	}
	
	public String getNomeBatVBSGetData(){
		String nomeArquivo = geraCaminhoScripts()+"getdate.vbs";
		return nomeArquivo;
	}
	
	public void escreverNoArquivoBatMonkey(String caminhoLog, String idTablet, String pacoteApp, String caminhoADB, String quantTHR,	String quantTouch, 
			String quantMotion, String quantTrackball, String quantNav, String quantMajornav, String quantAppswitch, String quantAnyevent,
			String quantCount, String quantSeed){
	    try {
			   criaArquivoBatParaExecucaoMonkey();	
	           BufferedWriter out = new BufferedWriter(new FileWriter(getNomeBatExecucaoMonkey()));  
	           out.write("set \"CSCRIPTPATH=C:\\WINDOWS\\SYSTEM32\\\"");
	           out.write("\nset \"LOG=" + caminhoLog + "\""); 
	           out.write("\nset \"ID="+idTablet+"\""); 
	           out.write("\nset \"APP="+pacoteApp+"\""); 
	           out.write("\nset \"ADB="+caminhoADB+"\""); 
	           out.write("\nset \"THR="+quantTHR + "\""); 
	           out.write("\nset \"touch="+quantTouch +"\""); 
	           out.write("\nset \"motion="+quantMotion +"\""); 
	           out.write("\nset \"trackball="+quantTrackball +"\""); 
	           out.write("\nset \"nav="+quantNav +"\""); 
	           out.write("\nset \"majornav="+quantMajornav +"\""); 
	           out.write("\nset \"appswitch="+quantAppswitch +"\""); 
	           out.write("\nset \"anyevent="+quantAnyevent +"\""); 
	           out.write("\nset \"count="+quantCount +"\""); 
	           out.write("\nset \"seed="+quantSeed +"\"");
	           
	           
	           out.write("if exist %LOG% (");
	           out.write("\necho Please make a copy of previous Monkey test results before continuing.");
	           out.write("\necho Otherwise they will be overwritten!!!");
	           out.write("\necho.");
	           out.write("\necho Deleting...");
	           out.write("\nrmdir /S %LOG%");
	           out.write("\necho.)");
	           out.write("\nif not exist %LOG% mkdir %LOG%");
	           out.write("\necho %TIME%   %DATE% - start of Monkey test > %LOG%test_time.log");
	           out.write("\necho %TIME%   %DATE% - start of Monkey test");
	           out.write("\necho Logging to %LOG%");
	           out.write("\necho Monkey test will take a while.");
	           out.write("\necho Do not terminate this job unless you really have to.");
	           out.write("\necho Please wait for test end or crash...");
	           out.write("\n@echo on");
	           out.write("\n%ADB%adb -s %ID% shell monkey -p %APP% --pct-touch %touch% --pct-motion %motion% --pct-trackball %trackball% --pct-nav %nav% --pct-majornav %majornav% --pct-appswitch %appswitch% --pct-anyevent %anyevent% -s %seed% -v -v --throttle %THR% %count% > %LOG%ScreenLog.log");
	           out.write("\n@echo off");
	           out.write("\necho %TIME%   %DATE% - end of Monkey test >> %LOG%test_time.log");
	           out.write("\necho %TIME%   %DATE% - end of Monkey test");
	           out.write("\necho.");
	           out.write("\necho Monkey test has ended/crashed.");
	           out.write("\necho Please wait until test data will be recorded in log files.");
	           out.write("\necho This may take a while...");
	           out.write("\n%ADB%adb -s %ID% shell dumpsys > %LOG%SystemLog.log");
	           out.write("\n%ADB%adb -s %ID% shell logcat -d ActivityManager:V %APP%:V > %LOG%ApplicationLog.log");
	           out.write("\nSET \"ID=\"");
	           out.write("\nSET \"APP=\"");
	           out.write("\nSET \"ADB=\"");
	           out.write("\nSET \"LOG=\"");
	           out.write("\nSET \"THR=\"");
	           out.write("\nSET \"touch=\"");
	           out.write("\nSET \"motion=\"");
	           out.write("\nSET \"trackball=\"");
	           out.write("\nSET \"majornav=\"");
	           out.write("\nSET \"appswitch=\"");
	           out.write("\nSET \"anyevent=\"");
	           out.write("\nSET \"count=\"");
	           out.write("\nSET \"seed=\"");
	           out.write("\necho.");
	           out.write("\necho !!! Monkey test is finished.");
	           out.write("\necho Consulte os logs em " + geraCaminhoParaCriarArquivosLog());
	       	
	           out.write("\necho.");
	           out.close();  
	       } catch (IOException e) {  
	    	   e.printStackTrace();
	       }  
	}
	
	public void criaDiretorioScripts(){
		String caminhoScripts = getDesktopUsuarioLogado();
		caminhoScripts = caminhoScripts.concat("Monkey_Exerciser\\");
		
		 if (!new File(caminhoScripts).exists()) { // Verifica se o diretório existe.   
             (new File(caminhoScripts)).mkdir();// Cria o diretório
             System.out.println("Diretório criado com sucesso.");   
         }else{
        	 System.out.println("Diretório já existe.");
         }
	}
	
	public static void main(String[] args) throws IOException {
	}
	
}
