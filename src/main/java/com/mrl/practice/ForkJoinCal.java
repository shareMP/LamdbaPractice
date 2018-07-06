package com.mrl.practice;

import java.util.concurrent.RecursiveTask;

/**
 *  ForkJoin框架
 *   *  [功能详细描述]
 * @作者 lwqMR
 * @version [版本号, 2017年12月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class ForkJoinCal extends RecursiveTask<Long>
{

    /**
     * 
     */
    private static final long serialVersionUID = 1319813647433745556L;

    private long start;
    private long end;

    private static final long t = 10000;

    @Override
    protected Long compute() {

        long length = end - start;
        if(length <= t){
            long sum = 0;
            for(long i=start;i<end;i++){
                sum+=i;
            }
            return sum;
        }else{
            long middle = (start+end)/2;
            ForkJoinCal left = new ForkJoinCal(start,middle);
            left.fork();//拆分子任务，压入线程队列
            
            ForkJoinCal right = new ForkJoinCal(middle+1, end);
            right.fork();
            
            return left.join()+right.join();
        }
    }

    public ForkJoinCal(long start, long end) {
        super();
        this.start = start;
        this.end = end;
    }

}