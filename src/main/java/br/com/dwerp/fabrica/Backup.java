package br.com.dwerp.fabrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import br.com.dwerp.msn.FacesMessageUtil;

public class Backup {
	public static void fazBackup(String arquivo, String diretorio){
		File arq = new File(arquivo);
		if (arq.exists()){
			if (arq.length() > 0)
				arq.delete();
		}
		try {
			Process p = null;
			String linha = "";
			ProcessBuilder pb = new ProcessBuilder(diretorio+"pg_dump", "-h", "localhost", "-U", "postgres", "-F", "c", "-b", "-v", "-f", arquivo, "dwerp");
			pb.environment().put("PGPASSWORD", "postgres");
			pb.redirectErrorStream(true);
			p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((linha = reader.readLine()) != null){
				System.out.println(linha);
			}
			FacesMessageUtil.addMensagemInfo("Backup realizado com sucesso!");
		}catch (Exception e) {
			System.out.println("Não foi possível efetuar o backup");
		}
	}
}
