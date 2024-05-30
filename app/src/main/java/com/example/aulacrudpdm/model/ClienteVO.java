package com.example.aulacrudpdm.model;




public class ClienteVO {
    private int id;
    private String nome;
    private String email;


    public ClienteVO() {


    }


    public ClienteVO(int id,String email) {
        this.id = id;
        this.email = email;
    }


    public ClienteVO(String nome) {
        this.nome = nome;
    }




    public ClienteVO(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public ClienteVO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }




    public int getId() {
        return id;
    }








    public void setId(int id) {
        this.id = id;
    }








    public String getNome() {
        return nome;
    }








    public void setNome(String nome) {
        this.nome = nome;
    }








    public String getEmail() {
        return email;
    }








    public void setEmail(String email) {
        this.email = email;
    }
}
