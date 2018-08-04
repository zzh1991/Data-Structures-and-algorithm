## 排序

### 排序算法
* 选择排序：不断地选择剩下元素中最小值
* 插入排序：从后开始扫描找到合适位置（<=），以及数组移位
* 冒泡排序：持续比较相邻元素，将最大值放到最后
* 归并排序：将两个有序对数组归并成一个更大的有序数组
* 快速排序：找基准，前小后大，递归调用

#### 稳定性
只有直接插入排序和归并排序是稳定的

### Java 8 Sort
#### 自定义比较
- lambda 实现对象排序：`list.sort((a, b) -> a.getName().compareTo(b.getName()));`
- Comparators 比较器进行排序：`Collections.sort(list, Comparator.comparing(Object::getName));`
- 多个条件比较: `list,sort(Comparator.comparing(Object::getAge).thenComparing(Object::getName));`
- 反转：`list.sort(Comparator.comparing(Object::getName).reversed());`

#### 数组排序
- 数组排序：`Arrays.sort()` for like `int[]` array
- 针对数组的某个小范围进行排序: `Arrays.sort(int[] a, int fromIndex, int toIndex)`, `Arrays.parallelSort (int [] a, int fromIndex, int toIndex);`

#### 集合排序
- `Collections.sort()`

#### 字符串排序
- 自然排序: `stringList.sort(Comparator.naturalOrder())`, `stringList.sort(String::compareTo)`
- 不区分大小写: `stringList.sort(String::compareToIgnoreCase)`
