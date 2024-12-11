//cardCompare
int cardCompare(String card1, String card2) {
    String suits = "HCDS";
    String ranks = "2345678910JQKA";
    int suitIndex1 = suits.indexOf(card1.charAt(1));
    int suitIndex2 = suits.indexOf(card2.charAt(1));
    if (suitIndex1 < suitIndex2) {
        return -1;
    } else if (suitIndex1 > suitIndex2) {
        return 1;
    } else {
        int rankIndex1 = ranks.indexOf(card1.charAt(0));
        int rankIndex2 = ranks.indexOf(card2.charAt(0));
        if (rankIndex1 < rankIndex2) {
            return -1;
        } else if (rankIndex1 > rankIndex2) {
            return 1;
        } else {
            return 0;
        }
    }
}

// bubbleSort
ArrayList<String> bubbleSort(ArrayList<String> list) {
    int n = list.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (cardCompare(list.get(j), list.get(j + 1)) > 0) {
                String temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
            }
        }
    }
    return list;
}

// mergeSort
ArrayList<String> mergeSort(ArrayList<String> list) {
    if (list.size() <= 1) {
        return list;
    }
    int mid = list.size() / 2;
    ArrayList<String> left = new ArrayList<>(list.subList(0, mid));
    ArrayList<String> right = new ArrayList<>(list.subList(mid, list.size()));
    left = mergeSort(left);
    right = mergeSort(right);
    ArrayList<String> result = new ArrayList<>();
    int i = 0, j = 0;
    while (i < left.size() && j < right.size()) {
        if (cardCompare(left.get(i), right.get(j)) <= 0) {
            result.add(left.get(i));
            i++;
        } else {
            result.add(right.get(j));
            j++;
        }
    }
    while (i < left.size()) {
        result.add(left.get(i));
        i++;
    }
    while (j < right.size()) {
        result.add(right.get(j));
        j++;
    }
    return result;
}
// measureBubbleSort
long measureBubbleSort(String filename) {
    long startTime = System.currentTimeMillis(); 
    try (Scanner scanner = new Scanner(new File(filename))) {
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        bubbleSort(list); 
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return -1; 
    }
    long endTime = System.currentTimeMillis(); 
    return endTime - startTime;
}
//measureMergeSort
long measureMergeSort(String filename) {
    long startTime = System.currentTimeMillis(); 
    try (Scanner scanner = new Scanner(new File(filename))) {
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        mergeSort(list); 
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return -1; 
    }
    long endTime = System.currentTimeMillis(); 
    return endTime - startTime;
}
//sortComparison
void sortComparison(String[] filenames) {
    try (FileWriter writer = new FileWriter("sortComparison.csv")) {
        writer.append(",10, 100, 10000\n");
        long bubbleTimeFor10 = 0, bubbleTimeFor100 = 0, bubbleTimeFor10000 = 0;
        long mergeTimeFor10 = 0, mergeTimeFor100 = 0, mergeTimeFor10000 = 0;
        for (String filename : filenames) {
            if ("sort10.txt".equals(filename)) {
                bubbleTimeFor10 = measureBubbleSort(filename);
                mergeTimeFor10 = measureMergeSort(filename);
            } else if ("sort100.txt".equals(filename)) {
                bubbleTimeFor100 = measureBubbleSort(filename);
                mergeTimeFor100 = measureMergeSort(filename);
            } else if ("sort10000.txt".equals(filename)) {
                bubbleTimeFor10000 = measureBubbleSort(filename);
                mergeTimeFor10000 = measureMergeSort(filename);
            }
        }
        writer.append("bubbleSort, ").append(String.valueOf(bubbleTimeFor10)).append(", ").append(String.valueOf(bubbleTimeFor100)).append(", ").append(String.valueOf(bubbleTimeFor10000)).append("\n");
        writer.append("mergeSort, ").append(String.valueOf(mergeTimeFor10)).append(", ").append(String.valueOf(mergeTimeFor100)).append(", ").append(String.valueOf(mergeTimeFor10000)).append("\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
}


