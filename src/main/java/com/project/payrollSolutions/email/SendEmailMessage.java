package com.project.payrollSolutions.email;

public class SendEmailMessage {
    public static String createSubject(String name) {
        return name + " sua nova conta foi criada!";
    }

    public static String createText(String name, String document, String password) {
        return "Olá " + name + "! Seja bem-vindo(a) em nossa plataforma." +
                " Suas credenciais de acesso ao sistema estão logo abaixo. \n\n" +
                "============================================================= \n\n" +
                "Documento: " + document + "\n" +
                "Senha: " + password + "\n\n" +
                "============================================================= \n\n" +
                "Qualquer dúvida é só entrar em contato conosco.";
    }
}
