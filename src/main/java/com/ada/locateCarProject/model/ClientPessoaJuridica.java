package com.ada.locateCarProject.model;

import com.ada.locateCarProject.util.RentalDiscount;

public class ClientPessoaJuridica extends Client {
    private String cnpj;

    public ClientPessoaJuridica(String name, String telephone, RentalDiscount rentalDiscount,String cnpj ) {
        super(name, telephone, rentalDiscount);
        this.cnpj = cnpj;
    }

    public String getIdentifier(){
        return cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }

    public void updateClient(String name, String telephone, RentalDiscount rentalDiscount, String cnpj) {
        if (name != null) {
            this.name = name;
        }
        if (telephone != null) {
            this.telephone = telephone;
        }
        if (rentalDiscount != null) {
            this.rentalDiscount = rentalDiscount;
        }
        if(cnpj != null) {
            this.cnpj = cnpj;
        }
    }
    @Override
    public String toString() {
        return "Client Pessoa Fisica: "  +
                " | nome : " + name +
                " | telefone: " + telephone +
                " | CNPJ : " + cnpj
                ;
    }
}
