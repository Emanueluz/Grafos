/** @author Emanuel Norjosa Luz e Giovanni Sencioles */
package grafos;

import java.util.ArrayList;
 

public class Grafo<Tipo extends Comparable<Tipo>>{
    private ArrayList<Aresta<Tipo>>  arestas ;
    private ArrayList<Vertice<Tipo>> vertices;
    private int qArestas;
    
    Grafo( ){
        this.arestas = new ArrayList<Aresta<Tipo>>();
        this.vertices = new ArrayList<Vertice<Tipo>>();
     }
  


    
    public ArrayList<Aresta<Tipo>> getarestas(){
        return this.arestas;
    }
    public void setarestas(ArrayList<Aresta<Tipo>> A)
    {
            this.arestas=A;
    }
    public ArrayList<Vertice<Tipo>> getvertices(){
        return this.vertices;
    }
    public void setvertices(ArrayList<Vertice<Tipo>> A)
    {
         this.vertices= A;
    }
    public int getqArestas(){
        return this.qArestas;
    }
    public void setqArestas(int A)
    {
         this.qArestas=A;
    }
 
    public ArrayList<Aresta<Tipo>>  arestas_da_origem(Vertice vertice){

         ArrayList<Aresta<Tipo>> vizinhas= new ArrayList<Aresta<Tipo>>();
   
         for(int i=0;i==this.arestas.size();i++){
            if ((this.arestas.get(i)).getOrigem()==vertice){
                vizinhas.add((this.arestas.get(i)));
            
                
            
               }
    }
        return vizinhas; 
    }

    public void prim(){
        ArrayList v_vertices= new ArrayList<>();
        ArrayList v_aretas= new ArrayList<>();
        Vertice v_atual= this.vertices.get(0);
        Aresta a_atual= this.arestas.get(0);
        float soma= 0; 
        v_vertices.add(v_atual); // lista de vertices visitados 
        while(this.vertices.size() > v_vertices.size()){
            float f_aux=(float) 999999;
            for(int i =0; i<this.arestas.size();i++){
                if(this.arestas.get(i).getPeso()<f_aux && v_vertices.contains(this.arestas.get(i).getOrigem())==true && v_vertices.contains(this.arestas.get(i).getDestino())==false){
                    f_aux = this.arestas.get(i).getPeso();
                    v_atual=this.arestas.get(i).getDestino();
                    a_atual=this.arestas.get(i);
                    
                }
            }
            if(v_vertices.contains(v_atual)==false) {
                soma=soma+f_aux;
                v_vertices.add(v_atual);
                v_aretas.add(a_atual);
                System.out.println("origem:"+((Cidade)a_atual.getOrigem().getValor()).getNome()+"     destino:"+((Cidade)a_atual.getDestino().getValor()).getNome()+"   peso:"+a_atual.getPeso());
            }
        }
        System.out.println("soma total dos pesos: " +soma);
    }

    public float fmaximo(Vertice origem, Vertice destino){
        ArrayList<Aresta<Tipo>>  lista_de_arestas =this.arestas;
        float fmax = 0;
        ArrayList<Vertice<Tipo>> ver_bloqueados = new ArrayList<Vertice<Tipo>> ();
        ArrayList<Aresta<Tipo>>  ares_bloqueados =new ArrayList<Aresta<Tipo>>();
        System.out.print(((Cidade)origem.getValor()).getNome());
        System.out.print("==="+((Cidade)destino.getValor()).getNome());


        while(/* //ver_bloqueados.contains(origem)==false*/ fmax<2){
            float fluxo_atual=9999999;
            ArrayList<Vertice<Tipo>> passados = new ArrayList<Vertice<Tipo>> ();
            Vertice atual = origem;
            ArrayList caminho = new ArrayList() ;
 
            
            for (int i =0;i<lista_de_arestas.size();i++){
                
                for (int j =0;j<lista_de_arestas.size();j++){
                    if ((lista_de_arestas.get(j).getOrigem()==atual) &&
                    (lista_de_arestas.get(j).getPeso() >0) && 
                    passados.contains(lista_de_arestas.get(j).getDestino())==false && 
                    ver_bloqueados.contains(lista_de_arestas.get(j).getDestino())==false) 
                    {
                        if (fluxo_atual==9999999){fluxo_atual=lista_de_arestas.get(j).getPeso();}

                        
                        passados.add(atual);
                        caminho.add(j);
                        atual=lista_de_arestas.get(j).getDestino();
                        destino=lista_de_arestas.get(j).getDestino();
                         if(fluxo_atual>lista_de_arestas.get(j).getPeso()){
                            fluxo_atual=lista_de_arestas.get(j).getPeso();
                    }}}
            //
        }



        if (atual==destino){
        for (int y =0;y<caminho.size();y++){
            System.out.print(lista_de_arestas.get((int) caminho.get(y)).getPeso()+" === ");
            lista_de_arestas.get((int) caminho.get(y)).setPeso(lista_de_arestas.get((int) caminho.get(y)).getPeso()-fluxo_atual);
            System.out.println(lista_de_arestas.get((int) caminho.get(y)).getPeso());
         }}
        atual=origem; 
        int aux_ver=0;
        int conta_ares=0;
        
        //determina quais arestas e vertices nao podem mais ser usados para fazer caminhos
        for(int k=0; k<this.vertices.size();k++){
            for(int l=0; l<lista_de_arestas.size();l++){
                if(lista_de_arestas.get(l).getOrigem()==this.vertices.get(k)){
                    aux_ver=aux_ver+1;
                }
                if(lista_de_arestas.get(l).getPeso()==0){
                    conta_ares=conta_ares+1;
                }
            }
            if(aux_ver==conta_ares){
                ver_bloqueados.add(this.vertices.get(k));
                for(int q=0; q<lista_de_arestas.size();q++){
                    if(ver_bloqueados.contains(lista_de_arestas.get(q).getDestino())){
                        ares_bloqueados.add(lista_de_arestas.get(q));
                    }
                }
            }
        }
         

    
    }
        return 0;
    }
    }
 

