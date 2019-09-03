import java.util.*;

/**
 * @author zhihao zhang
 * @date 2019-09-03
 */

public class EditDistance {

    private static final int OUT_OF_START = -1;
    private static final String INSERT = "INSERT";
    private static final String SKIP = "SKIP";
    private static final String DELETE = "DELETE";
    private static final String REPLACE = "REPLACE";
    private static final String SPACE = " ";

    public static void main(String[] args) {
        String source = "rad";
        String target = "apple";
        System.out.println(smallestEditDistanceByDp(source, target));
//        System.out.println(smallestEditDistanceByDpAndOperation(source, target));
    }

    private static int smallestEditDistance(String source, String target) {
        if (isStringEmpty(source)) {
            return getStringSize(target);
        }
        if (isStringEmpty(target)) {
            return getStringSize(source);
        }
        int sourceIndex = source.length() - 1;
        int targetIndex = target.length() - 1;
        Map<String, Integer> memoMap = new HashMap<>(100);
//        return smallestEditDistanceByRecursion(source, target, sourceIndex, targetIndex);
        return smallestEditDistanceByRecursionAndMemo(source, target, sourceIndex, targetIndex, memoMap);
    }

    private static int smallestEditDistanceByRecursion(String source, String target,
                                                       int sourceIndex, int targetIndex) {
        if (sourceIndex == OUT_OF_START) {
            return targetIndex + 1;
        }
        if (targetIndex == OUT_OF_START) {
            return sourceIndex + 1;
        }

        if (source.charAt(sourceIndex) == target.charAt(targetIndex)) {
            return smallestEditDistanceByRecursion(source, target,
                    sourceIndex - 1, targetIndex - 1);
        }
        return getMinNumber(smallestEditDistanceByRecursion(source, target, sourceIndex - 1, targetIndex),
                smallestEditDistanceByRecursion(source, target, sourceIndex, targetIndex - 1),
                smallestEditDistanceByRecursion(source, target, sourceIndex - 1, targetIndex - 1)
                ) + 1;
    }

    private static int smallestEditDistanceByRecursionAndMemo(String source, String target,
                                                              int sourceIndex, int targetIndex,
                                                              Map<String, Integer> memoMap) {
        if (sourceIndex == OUT_OF_START) {
            return targetIndex + 1;
        }
        if (targetIndex == OUT_OF_START) {
            return sourceIndex + 1;
        }

        String memoKey = getMemoKey(sourceIndex, targetIndex);
        if (memoMap.containsKey(memoKey)) {
            return memoMap.get(memoKey);
        }

        if (source.charAt(sourceIndex) == target.charAt(targetIndex)) {
            memoMap.put(memoKey, smallestEditDistanceByRecursion(source, target,
                    sourceIndex - 1, targetIndex - 1));
            return memoMap.get(memoKey);

        }
        memoMap.put(memoKey, getMinNumber(smallestEditDistanceByRecursion(source, target, sourceIndex - 1, targetIndex),
                smallestEditDistanceByRecursion(source, target, sourceIndex, targetIndex - 1),
                smallestEditDistanceByRecursion(source, target, sourceIndex - 1, targetIndex - 1)
        ) + 1);
        return memoMap.get(memoKey);
    }

    private static int smallestEditDistanceByDp(String source, String target) {
        if (isStringEmpty(source)) {
            return getStringSize(target);
        }
        if (isStringEmpty(target)) {
            return getStringSize(source);
        }
        int sourceLength = source.length();
        int targetLength = target.length();
        int[][] dpTable = new int[sourceLength + 1][targetLength + 1];
        for (int sourceIndex = 0; sourceIndex <= sourceLength; sourceIndex++) {
            dpTable[sourceIndex][0] = sourceIndex;
        }
        for (int targetIndex = 0; targetIndex <= targetLength; targetIndex++) {
            dpTable[0][targetIndex] = targetIndex;
        }
        for (int sourceIndex = 1; sourceIndex <= sourceLength; sourceIndex++) {
            for (int targetIndex = 1; targetIndex <= targetLength; targetIndex++) {
                if (source.charAt(sourceIndex - 1) == target.charAt(targetIndex - 1)) {
                    dpTable[sourceIndex][targetIndex] = dpTable[sourceIndex - 1][targetIndex - 1];
                } else {
                    dpTable[sourceIndex][targetIndex] = getMinNumber(
                            dpTable[sourceIndex - 1][targetIndex],
                            dpTable[sourceIndex][targetIndex - 1],
                            dpTable[sourceIndex - 1][targetIndex - 1]
                    ) + 1;
                }
            }
        }
        return dpTable[sourceLength][targetLength];
    }

    private static int smallestEditDistanceByDpAndOperation(String source, String target) {
        int sourceLength = source.length();
        int targetLength = target.length();
        EditOperation[][] dpTable = new EditOperation[sourceLength + 1][targetLength + 1];
        for (int sourceIndex = 0; sourceIndex <= sourceLength; sourceIndex++) {
            dpTable[sourceIndex][0] = new EditOperation(sourceIndex, INSERT);
        }
        for (int targetIndex = 0; targetIndex <= targetLength; targetIndex++) {
            dpTable[0][targetIndex] = new EditOperation(targetIndex, INSERT);
        }
        for (int sourceIndex = 1; sourceIndex <= sourceLength; sourceIndex++) {
            for (int targetIndex = 1; targetIndex <= targetLength; targetIndex++) {
                if (source.charAt(sourceIndex - 1) == target.charAt(targetIndex - 1)) {
                    dpTable[sourceIndex][targetIndex] = new EditOperation(
                            dpTable[sourceIndex - 1][targetIndex - 1].getValue(),
                            SKIP);
                } else {
                    dpTable[sourceIndex][targetIndex] = getEditOperation(dpTable[sourceIndex - 1][targetIndex],
                            dpTable[sourceIndex][targetIndex - 1],
                            dpTable[sourceIndex - 1][targetIndex - 1]
                            );
                }
            }
        }
        int sourceIndex = sourceLength;
        int targetIndex = targetLength;
        List<String> operationList = new ArrayList<>(100);
        while (!(sourceIndex <= 0 && targetIndex <= 0)) {
            String operation = dpTable[sourceIndex][targetIndex].getOperation();
            switch (operation) {
                case DELETE:
                    sourceIndex -= 1;
                    operationList.add(sourceIndex + " " + DELETE.concat(SPACE).concat(String.valueOf(source.charAt(sourceIndex))));
                    break;
                case REPLACE:
                    sourceIndex -= 1;
                    targetIndex -= 1;
                    operationList.add(sourceIndex + " " + REPLACE.concat(SPACE).concat(String.valueOf(source.charAt(sourceIndex)))
                            .concat(SPACE).concat(String.valueOf(target.charAt(targetIndex))));
                    break;
                case INSERT:
                    targetIndex -= 1;
                    operationList.add(sourceIndex + " " + INSERT.concat(SPACE).concat(String.valueOf(target.charAt(targetIndex))));
                    break;
                case SKIP:
                    sourceIndex -= 1;
                    targetIndex -= 1;
                default:
                    break;
            }
        }
        Collections.reverse(operationList);
        operationList.forEach(System.out::println);
        return dpTable[sourceLength][targetLength].getValue();
    }

    private static boolean isStringEmpty(String text) {
        return Objects.isNull(text) || text.length() == 0;
    }

    private static int getStringSize(String text) {
        if (Objects.isNull(text)) {
            return 0;
        }
        return text.length();
    }

    private static int getMinNumber(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static String getMemoKey(int sourceIndex, int targetIndex) {
        return String.valueOf(sourceIndex).concat("_").concat(String.valueOf(targetIndex));
    }

    private static EditOperation getEditOperation(EditOperation a, EditOperation b, EditOperation c) {
        if (a.getValue() <= b.getValue() && a.getValue() <= c.getValue()) {
            return new EditOperation(a.getValue() + 1, DELETE);
        }
        if (c.getValue() <= a.getValue() && c.getValue() <= b.getValue()) {
            return new EditOperation(c.getValue() + 1, REPLACE);
        }
        return new EditOperation(b.getValue() + 1, INSERT);
    }

    static class EditOperation {
        private int value;
        private String operation;

        public EditOperation(int value, String operation) {
            this.value = value;
            this.operation = operation;
        }

        public int getValue() {
            return value;
        }

        public String getOperation() {
            return operation;
        }
    }
}
