package helper;

public enum Color {
    RESET {
        @Override
        public String toString() {
            return "\u001B[0m";
        }
    },
    BLACK {
        @Override
        public String toString() {
            return "\u001B[30m";
        }
    },
    RED {
        @Override
        public String toString() {
            return "\u001B[31m";
        }
    },
    GREEN {
        @Override
        public String toString() {
            return "\u001B[32m";
        }
    },
    YELLOW {
        @Override
        public String toString() {
            return "\u001B[33m";
        }
    },
    BLUE {
        @Override
        public String toString() {
            return "\u001B[34m";
        }
    },
    PURPLE {
        @Override
        public String toString() {
            return "\u001B[35m";
        }
    },
    CYAN {
        @Override
        public String toString() {
            return "\u001B[36m";
        }
    },
    WHITE {
        @Override
        public String toString() {
            return "\u001B[37m";
        }
    }
}
