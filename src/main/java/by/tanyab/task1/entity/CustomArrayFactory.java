package com.arrayapp.entity;

public class CustomArrayFactory {
    private CustomArrayFactory() {
    }

    public static CustomArray createEmptyArray() {
        return new CustomArray();
    }

    public static CustomArray createArray(int[] array) {
        return new CustomArray(array);
    }

    public static CustomArray createArrayFromString(String data) throws CustomArrayException {
        if (data == null || data.trim().isEmpty()) {
            return createEmptyArray();
        }

        String[] parts = data.split("[,\\s;\\-]+");
        int[] result = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            try {
                result[i] = Integer.parseInt(parts[i].trim());
            } catch (NumberFormatException e) {
                throw new CustomArrayException("Invalid number format: " + parts[i]);
            }
        }

        return new CustomArray(result);
    }
}