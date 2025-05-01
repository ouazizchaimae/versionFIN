import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import CharteChimiqueDetailAdminTabNavigation from '../../../../../../navigation/admin/expedition/CharteChimiqueDetailAdminTabNavigation';

const CharteChimiqueDetailAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <CharteChimiqueDetailAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default CharteChimiqueDetailAdmin;
