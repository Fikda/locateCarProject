package com.ada.locateCarProject.model;

public class ClientPessoJuridica extends Client {
    private String cnpj;

    public ClientPessoJuridica(String name, String telephone, RentalDiscount rentalDiscount) {
        super(name, telephone, rentalDiscount);
    }

    public String getIdentifier(){
        return cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }

    public void updateClient(String name, String telephone, RentalDiscount rentalDiscount,String cnpj) {
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
