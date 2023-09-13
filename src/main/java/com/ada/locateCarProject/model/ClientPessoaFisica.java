package com.ada.locateCarProject.model;

public class ClientPessoaFisica extends Client{

    private String cpf;
    public ClientPessoaFisica(String name, String telephone, RentalDiscount rentalDiscount, String cpf) {
        super(name, telephone, rentalDiscount);
        this.cpf = cpf;
    }
    @Override
    public String getIdentifier() {
        return cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Client Pessoa Fisica: "  +
                " | nome : " + name +
                " | telefone: " + telephone +
                " | CPF : " + cpf
                ;
    }
}
