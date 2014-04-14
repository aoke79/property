        
    
    function selectAll(sel)
    {
        for( j=0; j<sel.length; j++ )
        {
            sel.options[j].selected = true;
        }
        return true;
    }

    function empty(sel)
    {
        for( j=sel.length-1; j>=0; j-- )
        {
            sel.options[j] = null;
        }
        return true;
    }


    function SelectOneItem( smObject , smValue )
    {
        for( j=0; j< smObject.length; j++ )
        {
            smo = smObject.options[j];
            if ( smo.value == smValue )
            {
                smObject.options[j].selected = true;
                return ;
            }
        }
    }
    
    
    function SelectOneItemByText( smObject , smText )
    {
        for( j=0; j< smObject.length; j++ )
        {
            smo = smObject.options[j];
            
            if ( smo.text == smText )
            {
                smObject.options[j].selected = true;
                return ;
            }
        }
    }

    function RadioSelectOneItem( smObject , smValue )
    {
        for( j=0; j< smObject.length; j++ )
        {
            if ( smObject[j].value == smValue )
            {
                smObject[j].checked = true;
                return ;
            }
        }
    }

    function move( col1, col2)
    {
        if(col1.selectedIndex==-1) return;
          toMove = col1.options[ col1.selectedIndex ];
          opt = new Option( toMove.text, toMove.value, false, false );
          col1.options[col1.selectedIndex ] = null;
          col2.options[col2.length] = opt;
          col2.selectedIndex = col2.length-1;
          return true;
    }

    function multiMove(col1, col2)
    {
        array = new Array();
        for( var j=0; j<col1.length; j++ )
        {
            if(col1.options[j].selected )
            {
                toMove = col1.options[ j ];
                  opt = new Option( toMove.text, toMove.value, false, false );
                  col2.options[col2.length] = opt;
                 array[array.length] = toMove.value;
            }
        }
        for(var j=0;j<array.length;j++)
        {
            val = array[j]
            removeValue(col1,val)
        }
    }
    function copyItem( col1, col2)
    {
        if(col1.selectedIndex==-1) return;
          toMove = col1.options[ col1.selectedIndex ];
          opt = new Option( toMove.text, toMove.value, false, false );
          col2.options[col2.length] = opt;
          col2.selectedIndex = col2.length-1;
          return true;
    }

    function addItem(name,value,col2)
    {
          opt = new Option( name, value, false, false );
          col2.options[col2.length] = opt;
          col2.selectedIndex = col2.length-1;
          return true;
    }

    function moveall(col1, col2)
    {
        while(col1.selectedIndex!=-1)
        {
            move(col1, col2);
        }
    }

    function copy( col1, col2)
    {
        for( j=0; j<col1.length; j++ )
        {
            toMove = col1.options[j];
            opt = new Option( toMove.text, toMove.value, false, false );
            col2.options[col2.length] = opt;
        }
    }


    function remove( col1)
    {
      col1.options[ col1.selectedIndex ] = null;
      return true;
    }

    function removeValue(smObject,smValue)
    {
        for( j=0; j< smObject.length; j++ )
        {
            if ( smObject.options[j].value == smValue )
            {
                smObject.options[j] = null
                return ;
            }
        }
    }

    function up( col1 )
    {
      index = col1.selectedIndex;
      if( index <= 0 )
        return true;

      toMoveX = col1.options[ index -1 ];
      toMoveY = col1.options[ index ];
      optX = new Option( toMoveX.text, toMoveX.value, false, false );
      optY = new Option( toMoveY.text, toMoveY.value, false, false );
      col1.options[index] = optX;
      col1.options[index-1] = optY;
      col1.selectedIndex = index-1;
      return true;
    }

   function down( col1 )
    {
      index = col1.selectedIndex;
      if( index < 0 )
        return true;

      if( index+1 >=  col1.options.length )
        return true;

      toMoveX = col1.options[ index ];
      toMoveY = col1.options[ index + 1 ];
      optX = new Option( toMoveX.text, toMoveX.value, false, false );
      optY = new Option( toMoveY.text, toMoveY.value, false, false );
      col1.options[index] = optY;
      col1.options[index+1] = optX;
      col1.selectedIndex = index+1;

      return true;
    }

    function mulitUp(col){
		var index = col.selectedIndex;
		if( index <= 0 ) return true;
		for( var j=0; j<col.length; j++ ) {
            if(col.options[j].selected ){
				var s1=col.options[j-1]
				  opt1 = new Option( s1.text, s1.value, false, false );
				var s2=col.options[j]
                  opt2 = new Option( s2.text, s2.value, true, true );
				 col.options[j-1] = opt2;
                 col.options[j] = opt1;
            }
        }
		
	}
	function mulitDown(col){
		var size=col.length-1;
		 for( var j=size; j>-1; j-- ){
            if(col.options[j].selected ) {
				if(j==size)return;
				var s2=col.options[j]
                 opt2 = new Option( s2.text, s2.value, true, true );
				var s1=col.options[j+1]
				  opt1 = new Option( s1.text, s1.value, false, false );
				
				 col.options[j+1] = opt2;
                 col.options[j] = opt1;
            }
        }	
	}
