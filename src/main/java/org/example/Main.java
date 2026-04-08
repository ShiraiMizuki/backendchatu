package org.example;
import io.javalin.Javalin;
import java.util.ArrayList;
import java.util.List;
//http://localhost:8080/wiadomosci
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static List<String> wiadomosci = new ArrayList<>();
    public static void main(String[] args) {
                int port = Integer.parseInt(System.getenv().getOrDefault("PORT" , "8080"));
                Javalin backend = Javalin.create(config ->{
                    config.bundledPlugins.enableCors(cors -> cors.addRule(it -> it.anyHost()));
                }).start(port);
//wyslanie wiadomosci (post)
        backend.get("/wiadomosci" , ctx->{
            ctx.json(wiadomosci);});
// pobieranie wiadomosci(get (boze nwm jak ja mam to rozwinac dobrze))
        backend.post("/send" , ctx -> {
            String tekst_android = ctx.body(); //pobranie tekstu z aplikacji
            if (tekst_android!=null && !tekst_android.isBlank()){
                wiadomosci.add(tekst_android);
                System.out.println("nowa wiadomosc : " +tekst_android);
                ctx.status(201).result("zapisane");
            }else {
                ctx.status(400).result("pusta wiadomosc");
            }
        });
        }
    }
