package com.cll.toy.filebrowerlib.bean

/**
 * Created by cll on 2018-08-29.
 */

class FileBean{
    internal constructor(){
    }
    internal constructor(head : String){
        file_head_letter = head;
    }
    internal var file_name : String? = null;
    internal var file_image : Int = 0;
    internal var file_size : String? = null;
    internal var file_path : String? = null;
    internal var file_head_letter : String? = null;




}
