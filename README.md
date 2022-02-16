# 
## 修改的地方
### 位置一
	public ArrayBlockingQueue<Integer>  queue=new ArrayBlockingQueue<>(COUNT);
  添加了一个线程安全的队列做中转
  
### 位置二
  alist[i] = list.indexOf(offset * size + i);
  alist[i] = list.get(offset * size + i);
  这个计算中的问题修复了
  
mac电脑
8 G内存 i5自测的结果：


自定义A方法计算时长: 25
自定义A和基准比较: 0.27778
