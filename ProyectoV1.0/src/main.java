import Procesos.Proceso;
import Procesos.Sistema;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList <Proceso> ListaProcesos = new ArrayList<>();
        ArrayList <Proceso> ProcesoEspera = new ArrayList<>();
        ArrayList <Proceso> ListaAux = new ArrayList<>();
        ArrayList <Proceso> ListaAux2 = new ArrayList<>();
        Sistema sistemaAux = new Sistema();
        Sistema sistemaAux2 = new Sistema();
        Sistema sistema = new Sistema();
        
        int stop=0;
        int vuelta=0;
        long inicio,finale;
        
        inicio = System.currentTimeMillis();
        sistema.setTiempoInicio(inicio);
        
        System.out.print("Tamano de la memoria: ");
        sistema.setMemoriaTotal(in.nextInt());
        sistemaAux.setMemoriaTotal(sistema.getMemoriaTotal());
        sistemaAux2.setMemoriaTotal(sistema.getMemoriaTotal());
        System.out.print("\nTamano de memoria maximo por proceso: ");
        sistema.setLimiteMemoria(in.nextInt());
        sistemaAux.setLimiteMemoria(sistema.getLimiteMemoria());
        sistemaAux2.setLimiteMemoria(sistema.getLimiteMemoria());
        System.out.print("\nMaximo de cuantos: ");
        sistema.setCuantos(in.nextInt());
        sistemaAux.setCuantos(sistema.getCuantos());
        sistemaAux2.setCuantos(sistema.getCuantos());
        System.out.print("\nMaximo de ciclos: ");
        vuelta = in.nextInt();
        System.out.print("\nMaximo de cuanto por proceso: ");
        sistema.setCuantoMaxPorProceso(in.nextInt());
        sistemaAux.setCuantoMaxPorProceso(sistema.getCuantoMaxPorProceso());
        sistemaAux2.setCuantoMaxPorProceso(sistema.getCuantoMaxPorProceso());
        System.out.println("\nTipo de ajuste: \n1.- Primer ajuste\n2.-Mejor ajuste\n3.-Peor ajuste");
        System.out.print("\nRespuesta: ");
        int tipo = in.nextInt();
        sistema.setTipoColocacion(tipo);
        switch(tipo)
        {
            case 1:
                sistemaAux.setTipoColocacion(2);
                sistemaAux2.setTipoColocacion(3);
                break;
            case 2:
                sistemaAux.setTipoColocacion(1);
                sistemaAux2.setTipoColocacion(3);
                break;
            case 3:
                sistemaAux.setTipoColocacion(1);
                sistemaAux2.setTipoColocacion(2);
                break;
        }
        
        IniciarMemoria(ListaProcesos,sistema);
        
        
        //Cuando RoundRobin se implemente quitar algoritmo de swapRobin
        for(int i=0;i<vuelta;i++){
            System.out.println("Ciclo: "+(i+1));
            if((ListaProcesos.size()>2&&i>1)&&(!sistema.isBanSwap())){
                SwapRobin(ListaProcesos,sistema);
            }
            if(sistema.isBanSwap())
                sistema.setBanSwap(false);
            mostrarProcesos(ListaProcesos,i+1);
            InsertarProceso(ListaProcesos,sistema,ProcesoEspera,i,ListaAux,ListaAux2);
            mostrarProcesos(ListaProcesos,i+1);
            ReducirCuanto(ListaProcesos,sistema,ProcesoEspera);
            mostrarProcesos(ListaProcesos,i+1);
            
            JuntaMemoria(ListaProcesos,sistema);
            System.out.println("Cuantos de sistema: "+sistema.getCuenta());
            if(sistema.getCuenta()>=vuelta)
            {
                break;
            }
            reiniciarBanderas(ListaProcesos);
            //GRafica 2
            MemoriaOcupada(sistema,ListaProcesos);
            System.out.println("");
            stop++;
        }    
        
        Memoria(ListaProcesos);
        finale = System.currentTimeMillis();
        sistema.setTiempoFinal(finale);
        
        System.out.println("Tiempo de ejecucion: "+(sistema.getTiempoFinal()-sistema.getTiempoInicio())/1000+ " segundos");
        System.out.println("Ciclos del sistema: "+sistema.getNoCuanto());
        
        //VUELTA 2
        ProcesoEspera.clear();
        ListaProcesos.clear();
        IniciarMemoria(ListaProcesos,sistemaAux);
        sistemaAux.setCont(0);
        for(int i=0;i<vuelta;i++){
            if((ListaProcesos.size()>2&&i>1)&&(!sistemaAux.isBanSwap())){
                SwapRobin(ListaProcesos,sistemaAux);
            }
            if(sistemaAux.isBanSwap())
                sistemaAux.setBanSwap(false);
            InsertarProcesoAux(ListaProcesos,sistemaAux,ProcesoEspera,i,ListaAux);
            ReducirCuanto(ListaProcesos,sistemaAux,ProcesoEspera);
            JuntaMemoria(ListaProcesos,sistemaAux);
            if(sistemaAux.getCuenta()>=vuelta)
            {
                break;
            }
            reiniciarBanderas(ListaProcesos);
            //GRafica 2
            MemoriaOcupada(sistemaAux,ListaProcesos);
        }  
        
        
        //VUELTA 3
        ProcesoEspera.clear();
        sistemaAux2.setNumeroProceso(0);
        ListaProcesos.clear();
        IniciarMemoria(ListaProcesos,sistemaAux2);
        sistemaAux2.setCont(0);
        for(int i=0;i<vuelta;i++){
            System.out.println("Ciclo: "+(i+1));
            if((ListaProcesos.size()>2&&i>1)&&(!sistemaAux2.isBanSwap())){
                SwapRobin(ListaProcesos,sistemaAux2);
            }
            if(sistemaAux2.isBanSwap())
                sistemaAux2.setBanSwap(false);
            mostrarProcesos(ListaProcesos,i+1);
            InsertarProcesoAux(ListaProcesos,sistemaAux2,ProcesoEspera,i,ListaAux2);
            mostrarProcesos(ListaProcesos,i+1);
            ReducirCuanto(ListaProcesos,sistemaAux2,ProcesoEspera);
            mostrarProcesos(ListaProcesos,i+1);
            
            JuntaMemoria(ListaProcesos,sistemaAux2);
            System.out.println("Cuantos de sistema: "+sistemaAux2.getCuenta());
            reiniciarBanderas(ListaProcesos);
            //GRafica 2
            MemoriaOcupada(sistemaAux2,ListaProcesos);
            System.out.println("");
            if(i>(stop-100))
                break;
        } 
        
        Tablas(sistema,sistemaAux,sistemaAux2);
        
    }
    
    //Graficas
    
    public static void MemoriaOcupada(Sistema sistema,ArrayList Procesos)
    {
        sistema.setVuelta(sistema.getVuelta()+1);
        sistema.setMemoriaOcupada(0);
        Proceso aux = null;
        for(int i=0;i<Procesos.size();i++)
        {
            aux = (Proceso) Procesos.get(i);
            if(aux.getCuanto()!=0&&aux.getID()!=0)
                sistema.setMemoriaOcupada(aux.getMemoria()+sistema.getMemoriaOcupada());
        }
        sistema.setPorcenParcial(sistema.getPorcenParcial()+((sistema.getMemoriaOcupada()*100)/sistema.getMemoriaTotal()));
        System.out.println(sistema.getPorcenParcial());
    }
    
    public static void Tablas(Sistema sistema,Sistema sistema2,Sistema sistema3)
    {
        for(int i=0;i<100;i++)
        {
            System.out.println("");
        }
        
        Operaciones(sistema);
        Operaciones(sistema2);
        Operaciones(sistema3);
        System.out.println("\t\tTiempo medio de atencion de Procesos(Cuantos)\tTotal Procesos atendidos\tPorcentaje de ocupacion de memoria");
        switch(sistema.getTipoColocacion())
        {
            case 1:
                System.out.println("Primer ajuste\t\t\t"+sistema.getTiempoAtenProceso()+"\t\t\t\t"+sistema.getNumeroProceso()+"\t\t\t\t\t"+sistema.getPorcenTotal()+"%");
                break;
            case 2:
                System.out.println("Mejor ajuste\t\t\t"+sistema.getTiempoAtenProceso()+"\t\t\t\t"+sistema.getNumeroProceso()+"\t\t\t\t\t"+sistema.getPorcenTotal()+"%");
                break;
            case 3:
                System.out.println("Peor ajuste\t\t\t"+sistema.getTiempoAtenProceso()+"\t\t\t\t"+sistema.getNumeroProceso()+"\t\t\t\t\t"+sistema.getPorcenTotal()+"%");
                break;
        }
        switch(sistema2.getTipoColocacion())
        {
            case 1:
                System.out.println("Primer ajuste\t\t\t"+sistema2.getTiempoAtenProceso()+"\t\t\t\t"+sistema2.getNumeroProceso()+"\t\t\t\t\t"+sistema2.getPorcenTotal()+"%");
                break;
            case 2:
                System.out.println("Mejor ajuste\t\t\t"+sistema2.getTiempoAtenProceso()+"\t\t\t\t"+sistema2.getNumeroProceso()+"\t\t\t\t\t"+sistema2.getPorcenTotal()+"%");
                break;
            case 3:
                System.out.println("Peor ajuste\t\t\t"+sistema2.getTiempoAtenProceso()+"\t\t\t\t"+sistema2.getNumeroProceso()+"\t\t\t\t\t"+sistema2.getPorcenTotal()+"%");
                break;
        }
        switch(sistema3.getTipoColocacion())
        {
            case 1:
                System.out.println("Primer ajuste\t\t\t"+sistema3.getTiempoAtenProceso()+"\t\t\t\t"+sistema3.getNumeroProceso()+"\t\t\t\t\t"+sistema3.getPorcenTotal()+"%");
                break;
            case 2:
                System.out.println("Mejor ajuste\t\t\t"+sistema3.getTiempoAtenProceso()+"\t\t\t\t"+sistema3.getNumeroProceso()+"\t\t\t\t\t"+sistema3.getPorcenTotal()+"%");
                break;
            case 3:
                System.out.println("Peor ajuste\t\t\t"+sistema3.getTiempoAtenProceso()+"\t\t\t\t"+sistema3.getNumeroProceso()+"\t\t\t\t\t"+sistema3.getPorcenTotal()+"%");
                break;
        }
    }
    
    public static void Operaciones(Sistema sistema)
    {
        int x = sistema.getTotalDes();
        sistema.setTiempoAtenProceso(x/sistema.getNumeroProceso());
        
        sistema.setPorcenTotal((sistema.getPorcenParcial())/sistema.getVuelta());
    }
    
    public static void Memoria(ArrayList procesos)
    {
        Proceso actual=null,siguiente=null;
       
        for(int i =0;i<procesos.size();i++)
        {
            actual = (Proceso) procesos.get(i);
            if(i == procesos.size()-1)
                return;
            siguiente = (Proceso) procesos.get(i+1);
  
            if(actual.getCuanto()==0 && siguiente.getCuanto()==0)
            {
                siguiente.setMemoria(actual.getMemoria()+siguiente.getMemoria());
                siguiente.setID(0);
                procesos.remove(i);
            }
        }
        try{
            actual = (Proceso) procesos.get(0);
            siguiente  = (Proceso) procesos.get(1);
            actual.setMemoria(actual.getMemoria()+siguiente.getMemoria());
            procesos.remove(1);
        }catch(IndexOutOfBoundsException e){}
    }
    
    public static void IniciarMemoria(ArrayList procesos,Sistema sistema)
    {
        Proceso primero = new Proceso();
        primero.setID(0);
        primero.setMemoria(sistema.getMemoriaTotal());
        primero.setCuanto(0);
        primero.setDespachado(false);
        procesos.add(primero);
    }
    
    /**
     *
     * @param ListaProcesos
     * @param sistema
     * @param ProcesoEspera
     */
    public static void InsertarProceso(ArrayList ListaProcesos,Sistema sistema,ArrayList ProcesoEspera,int cont,ArrayList ListaAux,ArrayList ListaAux2)
    {
        Proceso espera;
        if(ProcesoEspera.isEmpty())
        {
            sistema.setNumeroProceso(sistema.getNumeroProceso()+1);
            Proceso nuevo =  new Proceso(sistema.getNumeroProceso(),sistema.getCuantos(),sistema.getLimiteMemoria(),false);
            
            Proceso x = new Proceso();
            x.setCuanto(nuevo.getCuanto());
            x.setCuantoMaxPorProceso(nuevo.getCuantoMaxPorProceso());
            x.setDespachado(false);
            x.setID(nuevo.getID());
            x.setMemoria(nuevo.getMemoria());
            ListaAux.add(x);
            
            Proceso a = new Proceso();
            a.setCuanto(nuevo.getCuanto());
            a.setCuantoMaxPorProceso(nuevo.getCuantoMaxPorProceso());
            a.setDespachado(false);
            a.setID(nuevo.getID());
            a.setMemoria(nuevo.getMemoria());
            ListaAux2.add(a);
            
            if(ListaProcesos.size()>3)
            {
                if(EspacioEnBloques(nuevo,ListaProcesos))
                {
                    switch(sistema.getTipoColocacion())
                    {
                        case 1:
                            System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                            PrimerAjuste(nuevo,ListaProcesos,sistema);
                            //sistema.setCuantos(sistema.getCuantos()+1);
                            break;
                        case 2:
                            System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                            MejorAjuste(nuevo,ListaProcesos,sistema);
                            //MEJOR AJUSTE
                            break;
                        case 3:
                            System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                            PeorAjuste(nuevo,ListaProcesos,sistema);
                            //PEOR AJUSTE
                            break;

                    }
                }
                else{
                        sistema.setCuenta(sistema.getCuenta()+1);
                        AsignarEspera(nuevo,ProcesoEspera);
                }
            }
            else
            {
                if(EspacioEnBloques(nuevo,ListaProcesos)){
                    System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                    AsignacionSinAlgoritmo(nuevo,ListaProcesos,sistema);
                }
                else
                {
                    sistema.setCuenta(sistema.getCuenta()+1);
                    AsignarEspera(nuevo,ProcesoEspera);
                }
            }
        }
        else
        {
            espera = (Proceso) ProcesoEspera.get(0);
            //HAY UNO EN ESPERA
            if(ListaProcesos.size()>3)
            {
                //ALGORITMOS DE COLOCACION
                if(EspacioEnBloques(espera,ListaProcesos))
                {
                    switch(sistema.getTipoColocacion())
                    {
                        case 1:
                            System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                            PrimerAjuste(espera,ListaProcesos,sistema);
                            ProcesoEspera.clear();
                            break;
                        case 2:
                            System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                            MejorAjuste(espera,ListaProcesos,sistema);
                            ProcesoEspera.clear();
                            //MEJOR AJUSTE
                            break;
                        case 3:
                            System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                            PeorAjuste(espera,ListaProcesos,sistema);
                            ProcesoEspera.clear();
                            //PEOR AJUSTE
                            break;
                    }
                }
                else{
                    System.out.println("El proceso: "+"["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"] sigue esperando");                       
                }
            }
            else
            {
               if(EspacioEnBloques(espera,ListaProcesos)){
                    System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                    AsignacionSinAlgoritmo(espera,ListaProcesos,sistema);
                    ProcesoEspera.clear();
                }
                else
                {
                    System.out.println("El proceso: "+"["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"] sigue esperando");                        
                } 
            }
        }     
    }
    
    public static void InsertarProcesoAux(ArrayList ListaProcesos,Sistema sistema,ArrayList ProcesoEspera,int cont,ArrayList ListaAux)
    {
        Proceso espera;
        if(ProcesoEspera.isEmpty())
        {
            Proceso nuevo = null;
            sistema.setNumeroProceso(sistema.getNumeroProceso()+1);
            if(sistema.getCont() >= ListaAux.size())
            {
                nuevo =  new Proceso(sistema.getNumeroProceso(),sistema.getCuantos(),sistema.getLimiteMemoria(),false);
            
            }else{
                nuevo = (Proceso) ListaAux.get(sistema.getCont());
                sistema.setCont(sistema.getCont()+1);
            }
            
            if(ListaProcesos.size()>3)
            {
                if(EspacioEnBloques(nuevo,ListaProcesos))
                {
                    switch(sistema.getTipoColocacion())
                    {
                        case 1:
                            System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                            PrimerAjuste(nuevo,ListaProcesos,sistema);
                            //sistema.setCuantos(sistema.getCuantos()+1);
                            break;
                        case 2:
                            System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                            MejorAjuste(nuevo,ListaProcesos,sistema);
                            //MEJOR AJUSTE
                            break;
                        case 3:
                            System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                            PeorAjuste(nuevo,ListaProcesos,sistema);
                            //PEOR AJUSTE
                            break;

                    }
                }
                else{
                        sistema.setCuenta(sistema.getCuenta()+1);
                        AsignarEspera(nuevo,ProcesoEspera);
                }
            }
            else
            {
                if(EspacioEnBloques(nuevo,ListaProcesos)){
                    System.out.println("Entra proceso: ["+nuevo.getID()+","+nuevo.getMemoria()+","+nuevo.getCuanto()+"]");
                    AsignacionSinAlgoritmo(nuevo,ListaProcesos,sistema);
                }
                else
                {
                    sistema.setCuenta(sistema.getCuenta()+1);
                    AsignarEspera(nuevo,ProcesoEspera);
                }
            }
        }
        else
        {
            espera = (Proceso) ProcesoEspera.get(0);
            //HAY UNO EN ESPERA
            if(ListaProcesos.size()>3)
            {
                //ALGORITMOS DE COLOCACION
                if(EspacioEnBloques(espera,ListaProcesos))
                {
                    switch(sistema.getTipoColocacion())
                    {
                        case 1:
                            System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                            PrimerAjuste(espera,ListaProcesos,sistema);
                            ProcesoEspera.clear();
                            break;
                        case 2:
                            System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                            MejorAjuste(espera,ListaProcesos,sistema);
                            ProcesoEspera.clear();
                            //MEJOR AJUSTE
                            break;
                        case 3:
                            System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                            PeorAjuste(espera,ListaProcesos,sistema);
                            ProcesoEspera.clear();
                            //PEOR AJUSTE
                            break;
                    }
                }
                else{
                    System.out.println("El proceso: "+"["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"] sigue esperando");                       
                }
            }
            else
            {
               if(EspacioEnBloques(espera,ListaProcesos)){
                    System.out.println("Entra proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]");
                    AsignacionSinAlgoritmo(espera,ListaProcesos,sistema);
                    ProcesoEspera.clear();
                }
                else
                {
                    System.out.println("El proceso: "+"["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"] sigue esperando");                        
                } 
            }
        }     
    }
    
    public static boolean EspacioEnBloques(Proceso nuevo,ArrayList ListaProcesos)
    {
        Proceso aux;
        
        for(int i=0;i<ListaProcesos.size();i++)
        {
            aux = (Proceso) ListaProcesos.get(i);
            if(nuevo.getMemoria()<= aux.getMemoria() && (aux.getCuanto()==0))
                return true;
        }
        return false;
    }
    
    public static void AsignarEspera(Proceso espera,ArrayList ListaEspera)
    {
        System.out.println("El proceso: ["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"]"+" entro en espera");             
        ListaEspera.add(0,espera);
    }
    
    public static void mostrarProcesos(ArrayList procesos,int ciclo)
    {
        Proceso aux;
        
        for(int i =0;i<procesos.size();i++)
        {
            aux = (Proceso) procesos.get(i);
            System.out.print("["+aux.getID() + ","+aux.getMemoria()+","+aux.getCuanto()+"] ");
        }
        System.out.println("");
    }
    
    public static void ReducirCuanto(ArrayList procesos,Sistema sistema,ArrayList espera)
    {
        //Incrementa cuanto de sistema(cuando entra al micro)
        sistema.setCuenta(sistema.getCuenta()+2);
        Proceso aux;
        for(int i=0;i<procesos.size();i++)
        {
            aux = (Proceso) procesos.get(i);
            if(aux.getCuanto()<=0)
            {
                //Incrementa cuanto de sistema (Sale de la memoria)
                
                sistema.setCuenta(sistema.getCuenta()+1);
                aux.setCuanto(0);
                aux.setDespachado(true);
                aux.setID(0);
            }else if(aux.isDespachado()==false){
                sistema.setTotalDes(sistema.getTotalDes()+1);
                //Incrementa cuanto de sistema (Ejecucion en el micro)
                sistema.setCuenta(sistema.getCuenta()+1);
                System.out.println("Despachando: ["+aux.getID()+","+aux.getMemoria()+","+aux.getCuanto()+"]");
                aux.setCuantoMaxPorProceso(aux.getCuantoMaxPorProceso()+1);
                aux.setCuanto(aux.getCuanto()-1); 
                aux.setDespachado(true);
                if(aux.getCuanto()<=0){
                    return;
                }
                if(!espera.isEmpty())
                    RoundRobin(procesos,sistema,espera,i);
                return;
            }      
            if(i==(procesos.size()-1)){
                reiniciarBanderas(procesos);
                i = -1;
            }    
        }
        
        //Incrementa cuanto de sistema (Sale del micro)
        sistema.setCuenta(sistema.getCuenta()+1);
    }
    
    public static void reiniciarBanderas(ArrayList procesos)
    {
        Proceso aux;
        for(int i=0;i<procesos.size();i++)
        {
            aux = (Proceso) procesos.get(i);
            aux.setDespachado(false);
        }
    }
    
    public static boolean memoriaLLena(ArrayList Procesos)
    {
        boolean ban=true;
        for(int i=0;i<Procesos.size();i++)
        {
            Proceso aux = (Proceso) Procesos.get(i);
            if(aux.getCuanto()==0)
            {
                ban=false;
                return ban;
            }
        }
        return ban;
    }

    public static void JuntaMemoria(ArrayList procesos,Sistema sistema)
    {
        Proceso actual=null,siguiente=null;
       
        for(int i =0;i<procesos.size();i++)
        {
            actual = (Proceso) procesos.get(i);
            if(i == procesos.size()-1)
                return;
            siguiente = (Proceso) procesos.get(i+1);
  
            if((actual.getCuanto()==0) && (siguiente.getCuanto()==0))
            {
                //Incrementa cuanto de sistema (compactar de memoria)
                sistema.setCuenta(sistema.getCuenta()+1);
                siguiente.setMemoria(actual.getMemoria()+siguiente.getMemoria());
                siguiente.setID(0);
                //siguiente.setDespachado();
                procesos.remove(i);
                i--;
            }
        }
    }
    //ALGORITMOS DE AJUSTES
    
    public static void  PrimerAjuste(Proceso nuevo, ArrayList ListaProcesos,Sistema sistema)
    {
        //Incrementa cuanto de sistema
        sistema.setCuenta(sistema.getCuenta()+1);
        Proceso aux;
        
        for(int i=0;i<ListaProcesos.size();i++)
        {
            aux = (Proceso) ListaProcesos.get(i);
            if((nuevo.getMemoria()<=aux.getMemoria()) && (aux.getCuanto()==0))
            {
                aux.setCuanto(nuevo.getCuanto());
                aux.setID(nuevo.getID());
                return;
            }
        }
    }
    
    public static void MejorAjuste(Proceso nuevo, ArrayList ListaProcesos,Sistema sistema) {
        //Incrementa cuanto de sistema
        sistema.setCuenta(sistema.getCuenta()+1);
        Proceso aux;
        Proceso uno = (Proceso) ListaProcesos.get(0);
        int tam=uno.getMemoria();
        int posicion = 0;
        
        for(int i=0; i<ListaProcesos.size(); i++){
            aux = (Proceso) ListaProcesos.get(i);
            
            if(aux.getMemoria()>= nuevo.getMemoria() && (aux.getCuanto()==0) && (aux.getID()==0)){
                if(aux.getMemoria() <= tam){ //Busca la posicion del bloque que tenga menos espacio y sea capaz de almacenar
                    tam=aux.getMemoria();
                    posicion=i;
                }
                
            }
        }
        aux = (Proceso) ListaProcesos.get(posicion); 
        if(aux.getID()!=0 || aux.getID()!=0){
            aux = (Proceso) ListaProcesos.get(ListaProcesos.size()-1);
            aux.setCuanto(nuevo.getCuanto());
            aux.setID(nuevo.getID());
        }else{
            aux.setCuanto(nuevo.getCuanto());
            aux.setID(nuevo.getID());
        }
    }
    
        public static void PeorAjuste(Proceso nuevo, ArrayList ListaProcesos,Sistema sistema) {
        //Incrementa cuanto de sistema
        sistema.setCuenta(sistema.getCuenta()+1);
        Proceso aux;
        Proceso uno = (Proceso) ListaProcesos.get(0);
        int tam=uno.getMemoria();
        int posicion = 0;
        
        for(int i=0; i<ListaProcesos.size(); i++){
            aux = (Proceso) ListaProcesos.get(i);
            
            if(aux.getMemoria()>= nuevo.getMemoria() && (aux.getCuanto()==0)){
                if(aux.getMemoria() >= tam){ //Busca la posicion del bloque que tenga mas espacio y sea capaz de almacenar
                    tam=aux.getMemoria();
                    posicion=i;
                }
                
            }
        }
        aux = (Proceso) ListaProcesos.get(posicion); 
        aux.setCuanto(nuevo.getCuanto());
        aux.setID(nuevo.getID());
    }
        
    public static void AsignacionSinAlgoritmo(Proceso nuevo,ArrayList ListaProcesos,Sistema sistema)
    {
        //Incrementa cuanto de sistema
        sistema.setCuenta(sistema.getCuenta()+1);
        //ASigna
        Proceso aux;
        
        for(int i=0;i<ListaProcesos.size();i++)
        {
            aux = (Proceso) ListaProcesos.get(i);
            if((nuevo.getMemoria()<=aux.getMemoria()) && (aux.getCuanto()==0))
            {
                ListaProcesos.add(i,nuevo);
                aux.setMemoria(aux.getMemoria()-nuevo.getMemoria());
                return;
            }
        }
    }
    
    //ROUND ROBIN
    public static void RoundRobin(ArrayList procesos,Sistema sistema,ArrayList ListaEspera,int i)
    {
        sistema.setCuenta(sistema.getCuenta()+1);
        Proceso actual = (Proceso) procesos.get(i);
        if(actual.getCuantoMaxPorProceso()>=sistema.getCuantoMaxPorProceso())
        {
            sistema.setBanSwap(true);
            sistema.setCuenta(sistema.getCuenta()+1);
            SwapEspera(procesos,ListaEspera,i);
        }/*else{
            actual.setCuantoMaxPorProceso(actual.getCuantoMaxPorProceso()+1);
            System.out.println(actual.getCuantoMaxPorProceso() + " "+sistema.getCuantoMaxPorProceso());
        }*/
    }
    
    public static void SwapRobin(ArrayList procesos,Sistema sistema)
    {
        Proceso aux = (Proceso) procesos.get(0);
        int ultimo = procesos.size()-1;
        Proceso penul = new Proceso();

        penul.setCuanto(aux.getCuanto());
        penul.setID(aux.getID());
        penul.setMemoria(aux.getMemoria());
        
        if(memoriaLLena(procesos))
        {
            procesos.add(aux);
            procesos.remove(0);
        }else{
            procesos.add(ultimo,aux);
            procesos.remove(0);
        }
    }
    
    public static void SwapEspera(ArrayList proceso,ArrayList Espera,int i)
    {
        Proceso espera = (Proceso) Espera.get(0);
        Proceso actual = (Proceso) proceso.get(i);
        
        if(espera.getMemoria()>actual.getMemoria())
        {
            System.out.println("Swap Round Robin no pudo implementarse debido a falta de memoria disponible en el bloque");
        } 
        else
        {
            //SWAP DE PROCESOS 
            System.out.println("Swap Roun Robin entra proceso: "+"["+espera.getID()+","+espera.getMemoria()+","+espera.getCuanto()+"] por ["+actual.getID()+","+actual.getMemoria()+","+actual.getCuanto()+"]");
            Proceso aux = actual;
            espera.setMemoria(actual.getMemoria());
            
            proceso.remove(i);
            proceso.add(i,espera);
            
            Espera.remove(0);
            Espera.add(0,aux);
        }
    }
}
