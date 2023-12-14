package com.scottejames.aoc2023.util;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN;

        public static Direction fromStr(String s) {
            char c = s.charAt(0);
            return switch (c) {
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                case 'U' -> UP;
                case 'D' -> DOWN;
                default -> throw new IllegalArgumentException("Unknown direction: " + c);
            };
        }
        public static Direction fromChar(char c) {
            return switch (c) {
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                case 'U' -> UP;
                case 'D' -> DOWN;
                default -> throw new IllegalArgumentException("Unknown direction: " + c);
            };
        }
        public static Direction direction(Point from, Point to){
            Point dist = from.delta(to);
            if ((dist.x> 0) && (dist.y>0)) throw new IllegalStateException("Not a cardinal direction " + dist);
            if (dist.x < 0)
                return RIGHT;
            if (dist.x > 0)
                return LEFT;
            if (dist.y > 0)
                return UP;
            if (dist.y < 0)
                return DOWN;
            throw new IllegalStateException("Not a cardinal direction " + dist);
        };
        public static char toChar(Direction d){
            char c = switch ( d ) {
                case LEFT -> '<';
                case RIGHT -> '>';
                case UP -> '^';
                case DOWN -> 'v';
            };
            return c;
        }
        public static char directionToChar(Point from, Point to){
            Direction c = direction(from,to);
            return toChar(c);
        }
        public Direction reverse(){
            Direction result = UP;  // does not matter we WILL reset it
            if (this == UP) result = DOWN;
            if (this == DOWN) result = UP;
            if (this == LEFT) result = RIGHT;
            if (this == RIGHT) result = LEFT;
            return result;
        }
    }

