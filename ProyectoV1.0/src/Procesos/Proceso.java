package Procesos;

public class Proceso {
    private int ID;
    private int memoria;
    private int cuanto;
    private boolean despachado;
    private int CuantoMaxPorProceso;

    public Proceso() {
        
    }

    public Proceso(int Numero,int Cuantos,int Memoria,boolean despachado)
    {
        this.CuantoMaxPorProceso=0;
        this.despachado = despachado;
        this.ID = Numero;
        while(true)
        {
            cuanto = (int) (Math.random()*Cuantos)+2;
            if(cuanto>0)
                break;
        }
        memoria = (int)(Math.random()*Memoria)+1;
        
    }

    public int getCuantoMaxPorProceso() {
        return CuantoMaxPorProceso;
    }

    public void setCuantoMaxPorProceso(int CuantoMaxPorProceso) {
        this.CuantoMaxPorProceso = CuantoMaxPorProceso;
    }

    public boolean isDespachado() {
        return despachado;
    }

    public void setDespachado(boolean despachado) {
        this.despachado = despachado;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public int getCuanto() {
        return cuanto;
    }

    public void setCuanto(int cuanto) {
        this.cuanto = cuanto;
    }
    
    
}
