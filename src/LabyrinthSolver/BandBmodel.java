package LabyrinthSolver;

import java.util.ArrayList;

import edu.salleurl.arcade.labyrinth.model.enums.Direction;

public class BandBmodel implements Comparable<BandBmodel> {
    private ArrayList<Direction> configuracio;
    private int k;
    private int valorEstimado;

    public BandBmodel() {
        configuracio = new ArrayList<Direction>();
        k = 0;
        setValorEstimado(Integer.MAX_VALUE);
    }

    /**
     * @return the valorEstimado
     */
    public int getValorEstimado() {
        return valorEstimado;
    }

    /**
     * @param valorEstimado the valorEstimado to set
     */
    public void setValorEstimado(int valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    /**
     * @return the configuracio
     */
    public void addConfiguracio(Direction d) {
        configuracio.add(d);
    }

    /**
     * @return the configuracio
     */
    public ArrayList<Direction> getConfiguracio() {
        return configuracio;
    }

    /**
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * @param k the k to set
     */
    public void setK(int k) {
        this.k = k;
    }

    /**
     * @param configuracio the configuracio to set
     */
    public void setConfiguracio(ArrayList<Direction> configuracio) {
        this.configuracio = configuracio;
    }

    @Override
    public int compareTo(BandBmodel o) {
        return valorEstimado - o.getValorEstimado();
    }

    @Override
    public String toString() {
        return "Configuracio: " + configuracio + " k: " + k + " valorEstimado: " + valorEstimado + "\n";
    }

}
