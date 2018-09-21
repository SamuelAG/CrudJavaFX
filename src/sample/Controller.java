package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Conta.Conta;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Conta> tblContas;

    @FXML
    private TableColumn<Conta, String> clEmpresa;

    @FXML
    private TableColumn<Conta, String> clDescrição;

    @FXML
    private TableColumn<Conta, String> clDataVencimento;

    @FXML
    private TextField txtEmpresa;

    @FXML
    private TextField txtDescrição;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnLimpar;

    private List<Conta> contas = new ArrayList<>();

    @FXML
    public void Apagar() {
        Conta c = tblContas.getSelectionModel().getSelectedItem();
        contas.remove(c);
        atualizarTabela();
    }

    @FXML
    public void Atualizar() {
        Conta c =  tblContas.getSelectionModel().getSelectedItem();
        pegarValores(c);
        atualizarTabela();
    }

    @FXML
    public void limpar() {
        tblContas.getSelectionModel().select(null);
        txtDescrição.setText("");
        txtEmpresa.setText("");
        dataPicker.setValue(null);
    }

    @FXML
    public void Salvar() {
        Conta c = new Conta();
        pegarValores(c);
        contas.add(c);
        atualizarTabela();
    }

    public void atualizarTabela(){
        tblContas.getItems().setAll(contas);
        limpar();
    }

    public void pegarValores(Conta conta){
        conta.setEmpresa(txtEmpresa.getText());
        conta.setDescricao(txtDescrição.getText());
        conta.setDataVencimento(dataSelecionada());
    }

    private Date dataSelecionada(){
        LocalDateTime time = dataPicker.getValue().atStartOfDay();
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    private void configuraColunas(){
        clEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        clDescrição.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        clDataVencimento.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        atualizarTabela();
    }
}
