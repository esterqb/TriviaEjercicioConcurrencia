public class Pregunta {
    private String enunciado;
    private String respuestaA;
    private String respuestaB;
    private String respuestaC;
    private String respuestaD;
    private String respuestaCorrecta;

    public Pregunta(String enunciado, String respuestaA, String respuestaB, String respuestaC, String respuestaD, String respuestaCorrecta) {
        this.enunciado = enunciado;
        this.respuestaA = respuestaA;
        this.respuestaB = respuestaB;
        this.respuestaC = respuestaC;
        this.respuestaD = respuestaD;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getEnunciado() { return enunciado; }
    public String getRespuestaA() { return respuestaA; }
    public String getRespuestaB() { return respuestaB; }
    public String getRespuestaC() { return respuestaC; }
    public String getRespuestaD() { return respuestaD; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
}
