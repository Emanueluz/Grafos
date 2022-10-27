
package grafos;
import grafos.Grafo;
import grafos.Vertice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import grafos.Cidade;
public class main <Tipo extends Comparable<Tipo>>{
    static Grafo grafo;
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner nome_cidade = new Scanner(System.in);
        String matricula = "";
        int menu = 0;
        LeitorArquivos leitor = new LeitorArquivos();
       
        try{
        grafo = leitor.ler("entrada.txt") ;}
        catch (IOException e) {
        e.printStackTrace();}
     
        while(menu!=99){
            System.out.println(
                "i.  Obter cidades vizinhas: digite 1\n"+
                "ii.  Obter todos os caminhos a partir de uma cidade digite 2\n"+
                "iii.  Sair: digite 99"
            );
            menu =scanner.nextInt();
            if(menu==1){ 
                System.out.println("digite o codigo da cidade que quer consultar :");
                int codigo_da_cidade = nome_cidade.nextInt();
                Vertice v =  grafo.achar_Vertice(codigo_da_cidade);
                if (v==null){
                    System.out.println("CIDADE NÃO LISTADA");
                }else{
                    ArrayList<Aresta> cidades_vizinhas = grafo.arestas_da_origem(grafo.achar_Vertice(codigo_da_cidade));
                    System.out.println("CODIGO DA CIDADE VIZINHA ;NOME DA CIDADE VIZINHA ; DISTANCIA DA ORIGEM "); 
                    for(int i=0;i==cidades_vizinhas.size();i++){
                        Cidade cidade_aux= ((cidades_vizinhas.get(i)).getDestino().getValor());
                        System.out.println(
                    
                        cidade_aux.getCodigo()+"  -----  "+
                        cidade_aux.getNome()+"  -----  "+
                        (cidades_vizinhas.get(i)).getPeso()+"\n"); 
                    }
                
                     //String[] cidade_vizinhas = new String();

                }
                nome_cidade.close();
            }
            else if(menu==2){} 
            else if(menu==99){}
            else{ 
                System.out.println("DADO DE ENTRADA INVALIDO ");}
    } 
}



}