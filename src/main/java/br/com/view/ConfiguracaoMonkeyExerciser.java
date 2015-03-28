package br.com.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.util.Utils;

public class ConfiguracaoMonkeyExerciser {

	private JFrame frmConfiguraoMonkeyExerciser;
	private JTextField textFieldPacoteAplicacao;
	private JTextField textFieldCaminhoLogs;
	private JTextField textFieldCaminhoADB;
	private JTextField textFieldThrottle;
	private JTextField textFieldTouch;
	private JTextField textFieldMotion;
	private JTextField textFieldTrackball;
	private JTextField textFieldNav;
	private JTextField textFieldMajornav;
	private JTextField textFieldAppswitch;
	private JTextField textFieldAnyevent;
	private JTextField textFieldCount;
	private JTextField textFieldSeed;
	private JLabel lblCaminhoLogs;
	private JLabel lblIdDevices;
	private JLabel lblPacoteAplicao;
	private JLabel lblCaminhoAdb;
	private JLabel lblNewLabelThrottle;
	private JLabel lblTouch;
	private JLabel lblMotion;
	private JLabel lblTrackball;
	private JLabel lblNewLabelMajornav;
	private JLabel lblNav;
	private JLabel lblAppswitch;
	private JLabel lblAnyevent;
	private JLabel lblCount;
	private JLabel lblSeed;
	private JLabel lblConfiguraoDeEventos;
	private JLabel lblConfiguraoMonkeyExerciser;
	private JButton btnGerar;
	private JButton btnExecutar;
	private JComboBox<String> comboBoxDevicesConnectedes;
	private Utils utils = new Utils();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfiguracaoMonkeyExerciser window = new ConfiguracaoMonkeyExerciser();
					window.frmConfiguraoMonkeyExerciser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfiguracaoMonkeyExerciser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfiguraoMonkeyExerciser = new JFrame();
		frmConfiguraoMonkeyExerciser.setTitle("Configura\u00E7\u00E3o Monkey Exerciser");
		frmConfiguraoMonkeyExerciser.setBounds(100, 100, 341, 560);
		frmConfiguraoMonkeyExerciser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConfiguraoMonkeyExerciser.getContentPane().setLayout(null);
		frmConfiguraoMonkeyExerciser.setLocationRelativeTo(null);
		
		criaObjetosDaTela();
		sethintLabels();
		adicionaValoresComboBox();
		setValoresIniciais();
		adicionaCompomentesNoFomulario();
		setPosicaoItensNoFormulario();

		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldThrottle.getText().equals("0")){
					 JOptionPane.showMessageDialog(null, "Deixando o campo Throttle com o valor 0 não haverá delay entre os eventos.");
				}
				int soma = Integer.valueOf(textFieldTouch.getText())
						+ Integer.valueOf(textFieldMotion.getText())
						+ Integer.valueOf(textFieldTrackball.getText())
						+ Integer.valueOf(textFieldNav.getText())
						+ Integer.valueOf(textFieldMajornav.getText())
						+ Integer.valueOf(textFieldAppswitch.getText())
						+ Integer.valueOf(textFieldAnyevent.getText());
				
				if (soma <=100){
					 utils.escreverNoArquivoBatMonkey(textFieldCaminhoLogs.getText(), comboBoxDevicesConnectedes.getSelectedItem().toString().trim(), textFieldPacoteAplicacao.getText(),
							 textFieldCaminhoADB.getText(), textFieldThrottle.getText(),textFieldTouch.getText(),textFieldMotion.getText(), 
							 textFieldTrackball.getText(), textFieldNav.getText(), textFieldMajornav.getText(), textFieldAppswitch.getText(), 
							 textFieldAnyevent.getText(), textFieldCount.getText(), textFieldSeed.getText());
					 utils.escreverNoArquivoVBS();
					 JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso.");
				}else{
					JOptionPane.showMessageDialog(null, "A soma total dos eventos deve ser menor que 100%.");
				}
			}
		});
		
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bat = utils.getNomeBatExecucaoMonkey();
				try {
					JOptionPane.showMessageDialog(null, "Aguarde a execução do Monkey....... Isso pode demorar um pouco.");
					Runtime.getRuntime().exec("cmd /c start " + bat);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		});
	}
	
	public void criaObjetosDaTela(){
		lblCaminhoLogs= new JLabel("Caminho Logs:");
		lblIdDevices = new JLabel("ID Device:");
		lblPacoteAplicao = new JLabel("Pacote da Aplica\u00E7\u00E3o:");
		lblCaminhoAdb = new JLabel("Caminho Android SDK:");
		lblNewLabelThrottle = new JLabel("Throttle:");
		lblTouch = new JLabel("Touch:");
		lblMotion = new JLabel("Motion:");
		lblTrackball = new JLabel("Trackball:");
		lblNewLabelMajornav = new JLabel("Majornav:");
		lblNav = new JLabel("Nav:");
		lblAppswitch = new JLabel("Appswitch:");
		lblAnyevent = new JLabel("Anyevent:");
		lblCount = new JLabel("Count:");
		lblSeed = new JLabel("Seed:");
		btnGerar = new JButton("Gerar");
		btnExecutar = new JButton("Executar");
		textFieldMajornav = new JTextField();
		textFieldPacoteAplicacao = new JTextField();
		textFieldCaminhoLogs = new JTextField();
		textFieldCaminhoADB = new JTextField();
		textFieldThrottle = new JTextField();
		textFieldTouch = new JTextField();
		textFieldMotion = new JTextField();
		textFieldTrackball = new JTextField();
		textFieldNav = new JTextField();
		textFieldAppswitch = new JTextField();
		textFieldAnyevent = new JTextField();
		textFieldCount = new JTextField();
		textFieldSeed = new JTextField();
		comboBoxDevicesConnectedes= new JComboBox<String>();
		lblConfiguraoDeEventos = new JLabel("Defini\u00E7\u00E3o dos eventos:");
		lblConfiguraoDeEventos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfiguraoMonkeyExerciser = new JLabel("Configura\u00E7\u00E3o Monkey Exerciser");
		lblConfiguraoMonkeyExerciser.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
	
	public void sethintLabels(){
		lblCaminhoLogs.setToolTipText("Entre com o caminho de onde será salvo os logs da execução.");
		lblIdDevices.setToolTipText("Selecione um dispositivo para execução.");
		lblPacoteAplicao.setToolTipText("Entre com o pacote da aplicação a ser testada.");
		lblCaminhoAdb.setToolTipText("Entre com o caminho de instalação do Android SDK.");
		
		lblNewLabelThrottle.setToolTipText("Entre com o delay entre os eventos");
		lblTouch.setToolTipText("Entre com o percentual de eventos de toque.");
		lblMotion.setToolTipText("Entre com o percentual de eventos de movimentos aleatórios.");
		lblTrackball.setToolTipText("Entre com o percentual de eventos Trackball (Movimentos aleatórios, às vezes seguidos de um clique)");
		lblNav.setToolTipText("Entre com o percentual de eventos de navegação básica.");
		lblNewLabelMajornav.setToolTipText("Entre com o percentual de eventos de dentro da aplicação (Exemplo: Menu de configuração.)");
		lblAppswitch.setToolTipText("Entre com o percentual de eventos de troca de tela aleatóriamente.");
		lblAnyevent.setToolTipText("Entre com o percentual de eventos aleatórios.");
		lblCount.setToolTipText("Entre com a quantidade de eventos a serem lançados.");
		lblSeed.setToolTipText(" Entre com o número do numero do seed. Caso que voce queira executar exatamente o mesmo teste com a mesma ordem de um teste anterior coloque o numero do seed gerado, senão deixe como %RANDOM%.");
		lblConfiguraoDeEventos.setToolTipText("Somátoria do percentual de todos os eventos não pode ser maior do que 100%.");
	}
	
	public void adicionaValoresComboBox(){
		List<String> devices = utils.getDevice();
		for (String device : devices) {
			comboBoxDevicesConnectedes.addItem(device);	
		}
	}
	
	public void setValoresIniciais(){
		textFieldCaminhoLogs.setText(utils.geraCaminhoParaCriarArquivosLog());
		textFieldCaminhoADB.setText(utils.getPathADB());
		textFieldSeed.setText("%RANDOM%");
		textFieldMajornav.setText("0");
		textFieldThrottle.setText("0");
		textFieldTouch.setText("0");
		textFieldMotion.setText("0");
		textFieldTrackball.setText("0");
		textFieldNav.setText("0");
		textFieldAppswitch.setText("0");
		textFieldAnyevent.setText("0");
		textFieldCount.setText("0");
	}
	
	public void adicionaCompomentesNoFomulario(){
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblCaminhoLogs);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblIdDevices);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblPacoteAplicao);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldPacoteAplicacao);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldCaminhoLogs);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblCaminhoAdb);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldCaminhoADB);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblNewLabelThrottle);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldThrottle);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblTouch);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldTouch);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblMotion);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldMotion);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblTrackball);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblNav);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblNewLabelMajornav);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldTrackball);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldNav);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldMajornav);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblAppswitch);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblAnyevent);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblCount);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldAppswitch);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldAnyevent);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldCount);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblSeed);
		frmConfiguraoMonkeyExerciser.getContentPane().add(textFieldSeed);
		frmConfiguraoMonkeyExerciser.getContentPane().add(btnGerar);
		frmConfiguraoMonkeyExerciser.getContentPane().add(btnExecutar);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblConfiguraoDeEventos);
		frmConfiguraoMonkeyExerciser.getContentPane().add(lblConfiguraoMonkeyExerciser);
    	frmConfiguraoMonkeyExerciser.getContentPane().add(comboBoxDevicesConnectedes);
	}
	
	public void setPosicaoItensNoFormulario(){
		lblCaminhoLogs.setBounds(10, 39, 155, 14);
		lblIdDevices.setBounds(10, 82, 91, 14);
		lblPacoteAplicao.setBounds(10, 126, 194, 14);
		lblCaminhoAdb.setBounds(12, 169, 150, 14);
		lblNewLabelThrottle.setBounds(10, 218, 91, 14);
		lblTouch.setBounds(10, 300, 46, 14);
		lblMotion.setBounds(167, 345, 46, 14);
		lblTrackball.setBounds(10, 436, 69, 14);
		lblNav.setBounds(169, 300, 73, 14);
		textFieldCaminhoADB.setBounds(10, 187, 296, 20);
		textFieldThrottle.setBounds(10, 237, 86, 20);
		textFieldTouch.setBounds(10, 318, 142, 20);
		textFieldMotion.setBounds(166, 363, 140, 20);
		textFieldPacoteAplicacao.setBounds(9, 145, 297, 20);
		textFieldCaminhoLogs.setBounds(9, 57, 297, 20);
		lblNewLabelMajornav.setBounds(12, 390, 69, 14);
		textFieldTrackball.setBounds(10, 452, 296, 20);
		textFieldNav.setBounds(166, 318, 140, 20);
		textFieldMajornav.setBounds(10, 409, 142, 20);
		lblAppswitch.setBounds(12, 344, 89, 14);
		lblAnyevent.setBounds(167, 390, 85, 14);
		lblCount.setBounds(106, 218, 46, 14);
		textFieldAppswitch.setBounds(10, 363, 142, 20);
		textFieldAnyevent.setBounds(168, 409, 138, 20);
		textFieldCount.setBounds(106, 237, 86, 20);
		lblSeed.setBounds(204, 222, 91, 14);
		textFieldSeed.setBounds(202, 237, 104, 20);
		btnGerar.setBounds(65, 484, 89, 23);
		btnExecutar.setBounds(164, 484, 89, 23);
		lblConfiguraoDeEventos.setBounds(10, 276, 182, 14);
		comboBoxDevicesConnectedes.setBounds(10, 99, 296, 20);
		lblConfiguraoMonkeyExerciser.setBounds(51, 6, 228, 23);
		textFieldPacoteAplicacao.setColumns(10);
		textFieldCaminhoLogs.setColumns(10);
		textFieldCaminhoADB.setColumns(10);
		textFieldThrottle.setColumns(10);
		textFieldTouch.setColumns(10);
		textFieldMotion.setColumns(10);
		textFieldTrackball.setColumns(10);
		textFieldNav.setColumns(10);
		textFieldMajornav.setColumns(10);
		textFieldAppswitch.setColumns(10);
		textFieldCount.setColumns(10);
		textFieldAnyevent.setColumns(10);
		textFieldSeed.setColumns(10);
	}
}
