class Avg{
    public static void main(String[] args) {
        //double[] nums = new double[5];
    

        int length = Integer.parseInt(System.console().readLine());

        double[] nums = new double[length];

        for (int i=0; i<length; ++i) {
            nums[i] = Double.parseDouble(System.console().readLine());
        }

        double avg = 0;

        for(int i=0; i < nums.length; ++i) {
            avg += nums[i];
        }

        avg /= nums.length;

        double dx = Math.abs(avg - nums[0]);
        int index = 0;

        for(int i=1; i < nums.length; ++i) {
            if (Math.abs(avg - nums[i]) < dx) {
                dx = Math.abs(avg - nums[i]);
                index = i;
            }
        }

        System.out.println("Legkisebb eltérés az átlagtól: " + index + "\nÁtlag: " + avg);
    }
}