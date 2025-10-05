package com.masanta.ratan.daily.practice.leetcode.easy;

public class Maximum69Number {
    void main(){
        System.out.println(maximum69Number(9669));
        System.out.println(maximum69NumberWithoutConversion(669));
    }

    public int maximum69Number (int num) {
        String res = "", temp = String.valueOf(num);
        int n = temp.length();
        boolean changeDone = false;
        for(int i = 0; i < n; i++){
            if(!changeDone && temp.substring(i,i+1).equals("6")){
                res += "9";
                changeDone = true;
            } else {
                res += temp.substring(i,i+1);
            }
        }
        return Integer.valueOf(res);
    }

    public int maximum69NumberWithoutConversion (int num) {
        int n=num,r;
        int s=0;
        int c=0;
        int k=0;
        boolean p=false;
        while(n!=0){
            r=n%10;
            s=s*10+r;
            n=n/10;
            c++;
        }
        while(s!=0){
            r=s%10;
            if(r==6){
                p=true;
                break;
            }

            k=k+1;
            s=s/10;
        }
        int v=1;
        if(!p) return num;
        while(c!=k){
            v*=10;
            k++;

        }
        v=v/10;
        return num+3*v;
    }
}
