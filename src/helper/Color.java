package helper;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public enum Color {
    RESET {
        @Override
        public String toString() {
            return "\u001B[0m";
        }
    },
    BOLD {
        @Override
        public String toString() {
            return "\u001B[1m";
        }
    },
    ITALIC {
        @Override
        public String toString() {
            return "\u001B[3m";
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
    },
    BLACKBRIGHT {
        @Override
        public String toString() {
            return "\u001B[90m";
        }
    },
    REDBRIGHT {
        @Override
        public String toString() {
            return "\u001B[91m";
        }
    },
    GREENBRIGHT {
        @Override
        public String toString() {
            return "\u001B[92m";
        }
    },
    YELLOWBRIGHT {
        @Override
        public String toString() {
            return "\u001B[93m";
        }
    },
    BLUEBRIGHT {
        @Override
        public String toString() {
            return "\u001B[94m";
        }
    },
    PURPLEBRIGHT {
        @Override
        public String toString() {
            return "\u001B[95m";
        }
    },
    CYANBRIGHT {
        @Override
        public String toString() {
            return "\u001B[96m";
        }
    },
    WHITEBRIGHT {
        @Override
        public String toString() {
            return "\u001B[97m";
        }
    },
    BLACKBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[40m";
        }
    },
    REDBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[41m";
        }
    },
    GREENBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[42m";
        }
    },
    YELLOWBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[43m";
        }
    },
    BLUEBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[44m";
        }
    },
    PURPLEBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[45m";
        }
    },
    CYANBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[46m";
        }
    },
    GRAYBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[47m";
        }
    },
    WHITEBACKGROUND {
        @Override
        public String toString() {
            return "\u001B[48m";
        }
    },
    BLACKBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;100m";
        }
    },
    REDBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;101m";
        }
    },
    GREENBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;102m";
        }
    },
    YELLOWBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;103m";
        }
    },
    BLUEBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;104m";
        }
    },
    PURPLEBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;105m";
        }
    },
    CYANBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;106m";
        }
    },
    WHITEBACKGROUNDBRIGHT {
        @Override
        public String toString() {
            return "\033[0;107m";
        }
    },
}
