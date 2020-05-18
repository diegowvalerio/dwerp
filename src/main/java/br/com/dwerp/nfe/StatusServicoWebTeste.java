package br.com.dwerp.nfe;

import java.lang.invoke.MethodHandles;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;


/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 */
public class StatusServicoWebTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Certificado
            Certificado certificado = CertificadoService.certificadoPfx("Y:/certificados/teste-123456.pfx", "12345678");
            //Esse Objeto Voc� pode guardar em uma Session.
            
            ConfiguracoesNfe config = ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.PR,
                    AmbienteEnum.HOMOLOGACAO,
                    certificado,
                    MethodHandles.lookup().lookupClass().getResource("/schemas").getPath());

            TRetConsStatServ retorno = Nfe.statusServico(config, DocumentoEnum.NFE);
            System.out.println("Status:" + retorno.getCStat());
            System.out.println("Motivo:" + retorno.getXMotivo());
            System.out.println("Data:" + retorno.getDhRecbto());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}