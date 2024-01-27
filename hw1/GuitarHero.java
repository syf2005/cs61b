
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static void main(String[] args) {
        /* create all guitar strings */
        synthesizer.GuitarString[] stringAll = new synthesizer.GuitarString[37];
        for (int i = 0; i < 37; i++) {
            stringAll[i] = new synthesizer.GuitarString(440.0 * Math.pow(2, ((double) i - 24.0) / 12.0));
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (0 <= index && index < 37) {
                    stringAll[index].pluck();
                }
            }

            /* compute the superposition of samples */

            double sample = 0;
            for (int i = 0; i < 37; i++) {
                sample += stringAll[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                stringAll[i].tic();
            }
        }
    }
}



