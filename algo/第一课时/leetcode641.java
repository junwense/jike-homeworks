package com.sean;

/**
 * @ClassName leetcode641
 * @Description: TODO
 * @Author a9705
 * @Date 2022/7/22
 * @Version V1.0
 **/
public class leetcode641 {

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new leetcode641.MyCircularDeque(4);
        circularDeque.insertFront(9);
        circularDeque.deleteLast();
        circularDeque.getRear();
    }

    /**
     * 基于数组实现循环队列，难点是扩容。
     */
    static class MyCircularDeque {

        //用于存储元素
        private int[] myArrays;
        //数组容量
        private int cap;
        //头节点
        private int head = 0;
        //尾节点
        private int tail = 1;

        public MyCircularDeque(int k) {
            //默认是容量+2，因为需要2个位置存放节点
            this.myArrays = new int[k + 2];
            this.cap = k + 2;
        }

        /**
         * 时间复杂度 o(1)
         */
        public boolean insertFront(int value) {
            //队列满了
            if (isFull()) {
                return false;
            }

            myArrays[head] = value;
            head = (cap + head - 1) % cap;
            return true;
        }

        /**
         * 时间复杂度 o(1)
         */
        public boolean insertLast(int value) {
            //队列满了
            if (isFull()) {
                return false;
            }

            myArrays[tail] = value;
            tail = (tail + 1) % cap;
            return true;
        }

        /**
         * 时间复杂度 o(1)
         */
        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            head = (head + 1) % cap;
            myArrays[head] = -1;
            return true;
        }

        /**
         * 时间复杂度 o(1)
         */
        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            tail = (cap + tail - 1) % cap;
            myArrays[tail] = -1;

            return true;
        }

        /**
         * 时间复杂度 o(1)
         */
        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return myArrays[(head + 1) % cap];
        }

        /**
         * 时间复杂度 o(1)
         */
        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return myArrays[(cap + tail - 1) % cap];
        }

        //head在tail后面，证明数组为空 如tail是1 head是0
        public boolean isEmpty() {
            return (cap + tail - 1) % cap == head;
        }

        //head在tail前面，证明数组满了 如tail是1 head是2
        public boolean isFull() {
            return (tail + 1) % cap == head;
        }
    }
}
