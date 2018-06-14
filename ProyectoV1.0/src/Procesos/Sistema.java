package Procesos;

public class Sistema {
    private int cont;
    private int MemoriaTotal;
    private int limiteMemoria;
    private int cuantos,NoCuanto,CuantoMaxPorProceso;
    private int Cuenta;
    private int numeroProceso;
    private int TipoColocacion;
    private long TiempoInicio,TiempoFinal;
    private boolean banSwap;
    
    //Grafica atencion de proceso
    private int totalDes;
    private double TiempoAtenProceso;
    
    //Grafica porcentaje de ocupacion de memoria
    private int vuelta;
    private double porcenParcial,porcenTotal;
    private int memoriaOcupada;

    public Sistema() {
    }
    
    public Sistema(int memoriaMax, int cuantos, int numeroProceso) {
        NoCuanto =0;
        this.MemoriaTotal = memoriaMax;
        this.cuantos = cuantos;
        this.numeroProceso = numeroProceso;
    }

    public int getVuelta() {
        return vuelta;
    }

    public void setVuelta(int vuelta) {
        this.vuelta = vuelta;
    }

    
    
    public double getPorcenParcial() {
        return porcenParcial;
    }

    public void setPorcenParcial(double porcenParcial) {
        this.porcenParcial = porcenParcial;
    }

    public double getPorcenTotal() {
        return porcenTotal;
    }

    public void setPorcenTotal(double porcenTotal) {
        this.porcenTotal = porcenTotal;
    }

    public int getMemoriaOcupada() {
        return memoriaOcupada;
    }

    public void setMemoriaOcupada(int memoriaOcupada) {
        this.memoriaOcupada = memoriaOcupada;
    }
    
    

    public double getTiempoAtenProceso() {
        return TiempoAtenProceso;
    }

    public void setTiempoAtenProceso(double TiempoAtenProceso) {
        this.TiempoAtenProceso = TiempoAtenProceso;
    }

    public int getTotalDes() {
        return totalDes;
    }

    public void setTotalDes(int totalDes) {
        this.totalDes = totalDes;
    }
    
    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    
    
    public boolean isBanSwap() {
        return banSwap;
    }

    public void setBanSwap(boolean banSwap) {
        this.banSwap = banSwap;
    }

    
    
    public int getCuenta() {
        return Cuenta;
    }

    public void setCuenta(int Cuenta) {
        this.Cuenta = Cuenta;
    }
    
    

    public int getCuantoMaxPorProceso() {
        return CuantoMaxPorProceso;
    }

    public void setCuantoMaxPorProceso(int CuantoMaxPorProceso) {
        this.CuantoMaxPorProceso = CuantoMaxPorProceso;
    }

    public int getNoCuanto() {
        return NoCuanto;
    }

    public void setNoCuanto(int NoCuanto) {
        this.NoCuanto = NoCuanto;
    }
    
    

    public long getTiempoInicio() {
        return TiempoInicio;
    }

    public void setTiempoInicio(long TiempoInicio) {
        this.TiempoInicio = TiempoInicio;
    }

    public long getTiempoFinal() {
        return TiempoFinal;
    }

    public void setTiempoFinal(long TiempoFinal) {
        this.TiempoFinal = TiempoFinal;
    }

    
    
    public int getLimiteMemoria() {
        return limiteMemoria;
    }

    public void setLimiteMemoria(int limiteMemoria) {
        this.limiteMemoria = limiteMemoria;
    }

    
    public int getTipoColocacion() {
        return TipoColocacion;
    }

    public void setTipoColocacion(int TipoColocacion) {
        this.TipoColocacion = TipoColocacion;
    }

    
    
    public int getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(int numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public int getMemoriaTotal() {
        return MemoriaTotal;
    }

    public void setMemoriaTotal(int memoriaMax) {
        this.MemoriaTotal = memoriaMax;
    }

    public int getCuantos() {
        return cuantos;
    }

    public void setCuantos(int cuantos) {
        this.cuantos = cuantos;
    }
    
    
}
