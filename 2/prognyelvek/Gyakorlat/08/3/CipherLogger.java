public class CipherLogger extends Logger {
    @Override
    public void log(String str) {
        StringBuilder sb = new StringBuilder();
        for (char character : str.toCharArray()) {
            if (character != ' ') {
                int originalPosition = character - 'a';
                int newPosition = (originalPosition + 3) % 26;
                char newChar = (char) ('a' + newPosition);
                sb.append(newChar);
            } else {
                sb.append(character);
            }
        }
        System.out.println(sb.toString());
    }
}
