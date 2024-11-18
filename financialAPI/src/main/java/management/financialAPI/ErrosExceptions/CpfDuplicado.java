package management.financialAPI.errosExceptions;

    public class cpfDuplicado extends RuntimeException {
        public cpfDuplicado(String message) {
            super(message);
    }
}
