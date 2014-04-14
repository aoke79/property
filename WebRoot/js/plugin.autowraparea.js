	function autoRemake(args){
		if(!args || args.nodeType !== 1){
      		return false;
    	} else if(args.currentStyle && typeof(args.currentStyle.wordBreak) === 'string'){
      		breakWord = function(args){
        		args.runtimeStyle.wordBreak = 'break-all';
        		return true;
      		}
      		return breakWord(args);
    	} else if(document.createTreeWalker){
      		var trimcropper = function(str) {
		        str = str.replace(/^\s\s*/, '');
		        var alphaS = /\s/,
		        i = --str.length
		        while (alphaS.test(str.charAt(i)));
		        return str.slice(0, i + 1);
      		}
      		breakWord = function(args){
		        var docWalker = document.createTreeWalker(args, NodeFilter.SHOW_TEXT, null, false);
		        var charNode,charS,charC = String.fromCharCode('8203');
		        while (docWalker.nextNode()){
          			charNode = docWalker.currentNode;
			        charS = trimcropper(charNode.nodeValue).split('').join(charC);
			        charNode.nodeValue = charS;
        		}
        		return true;
      		}
      		return breakWord(args);
    	} else {
      		return false;
    	}
	}
	
