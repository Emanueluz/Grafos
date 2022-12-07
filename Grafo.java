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

    public void fmaximo(Vertice origem, Vertice destino){
        ArrayList<Aresta<Tipo>>  lista_de_arestas =this.arestas;
        float fmax = 0;
        ArrayList<Vertice<Tipo>> ver_bloqueados = new ArrayList<Vertice<Tipo>> ();
        ArrayList<Aresta<Tipo>>  ares_bloqueados =new ArrayList<Aresta<Tipo>>();
  

        while(ver_bloqueados.contains(origem)==false){
            float fluxo_atual=9999999;
            ArrayList<Vertice<Tipo>> passados = new ArrayList<Vertice<Tipo>> ();
            Vertice atual = origem;
            ArrayList caminho = new ArrayList() ;
            System.out.println( );

            for (int i =0;i<lista_de_arestas.size();i++){ //Caminhar o grafo até chegar ao destino
                for (int j =0;j<lista_de_arestas.size();j++){
                    if ((lista_de_arestas.get(j).getOrigem()==atual) &&  //Caso vertice atual esteja no comeco de dado caminho 
                    (lista_de_arestas.get(j).getPeso() >0) &&  //e estar incluso em um arco positivo
                    passados.contains(lista_de_arestas.get(j).getDestino())==false && //e o caminho para o vizinho nao tenha sido percorrido
                    ver_bloqueados.contains(lista_de_arestas.get(j).getDestino())==false&&
                    ares_bloqueados.contains(lista_de_arestas.get(j))==false)  //Adiciona o indice do atual ao caminho
                    {
                        if (fluxo_atual==9999999){fluxo_atual=lista_de_arestas.get(j).getPeso();}// dá a variavel axiliar o valor de fluxo da primeira aresta 

                        System.out.print(((Cidade)atual.getValor()).getNome()+"-");
                        passados.add(atual); //Novo atual incluso na lista de percorridos
                        caminho.add(j); //Adiciona o indice do atual ao caminho
                        atual=lista_de_arestas.get(j).getDestino(); //Atual recebe o vizinho
                        System.out.print(((Cidade)atual.getValor()).getNome()+"   ");
                         if(fluxo_atual>lista_de_arestas.get(j).getPeso()){
                            fluxo_atual=lista_de_arestas.get(j).getPeso();  //Fluxo maximo atualizado com o peso do arco
                    }}}
            //
        }

        fmax= fmax+fluxo_atual; // incremento do fluxo máximo
        // atualisa o valor de fluxo das arestas que formam o caminho 
        if (atual==destino){
        for (int y =0;y<caminho.size();y++){
            
            lista_de_arestas.get((int) caminho.get(y)).setPeso(lista_de_arestas.get((int) caminho.get(y)).getPeso()-fluxo_atual);
            if (lista_de_arestas.get((int) caminho.get(y)).getPeso()==0){
                ares_bloqueados.add(lista_de_arestas.get((int)caminho.get(y)));
            System.out.println(((Cidade)lista_de_arestas.get((int) caminho.get(y)).getOrigem().getValor()).getNome() +" === === == "+((Cidade)lista_de_arestas.get((int) caminho.get(y)).getDestino().getValor()).getNome());}
          }}
        
        atual=origem; 
        //determina quais arestas e vertices nao podem mais ser usados para fazer caminhos
        for(int k=0; k<this.vertices.size();k++){
            int aux_ver=0;
            int conta_ares=0;
           // System.out.println("verificando outro vertice ");

            for(int l=0; l<lista_de_arestas.size();l++){
                if(lista_de_arestas.get(l).getOrigem()==this.vertices.get(k)){
                    if(lista_de_arestas.get(l).getPeso()==0){
                        conta_ares=conta_ares+1;
                    }
                    aux_ver=aux_ver+1;
                   // System.out.println(aux_ver);
                }
            }
            if(aux_ver==conta_ares && aux_ver!=0){
                ver_bloqueados.add(this.vertices.get(k));
                 for(int q=0; q<lista_de_arestas.size();q++){
                    if(ver_bloqueados.contains(lista_de_arestas.get(q).getDestino())==true){
                        ares_bloqueados.add(lista_de_arestas.get(q));
                     }
                }
            }
        }
         

    
    }   System.out.println("O FLUXO MAXIMO DE "+((Cidade) origem.getValor()).getNome()+" até "+((Cidade) destino.getValor()).getNome()+" É "+ fmax);
           
    }
    }
 

